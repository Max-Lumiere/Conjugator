import Foundation
import Combine

protocol ResponderChain: AnyObject {
    func responderForLink(_ code: String) -> DeepLinkResponder?
}

extension BaseCoordinator: ResponderChain {
    func removeYoungerSiblings(of child: DeepLinkResponder) {
        for (key, sibling) in childCoordinators.toArray().reversed() where sibling.isSelfSustained {
            if sibling === child {
                return
            }
            childCoordinators.remove(key)
        }
    }

    func childResponder(_ code: String) -> DeepLinkResponder? {
        for (_, coordinator) in childCoordinators.reversed() {
            if let responder = coordinator.responderForLink(code) {
                return responder
            }
        }
        return nil
    }

    func responderForLink(_ code: String) -> DeepLinkResponder? {
        if canHandleLink(code) {
            return self
        }
        return childResponder(code)
    }
}

class BaseCoordinator<ResultType>: Identifiable, DeepLinkResponder {
    let id = UUID()
    var additionalCheck: (AnyObject) -> Void = { _ in }
    private var childCoordinators = IndexedList<UUID, DeepLinkResponder & ResponderChain>()
    private var cancellables = Set<AnyCancellable>()

    var isSelfSustained: Bool { true }

    private func store<T>(coordinator: BaseCoordinator<T>) {
        childCoordinators[coordinator.id] = coordinator
        coordinator.additionalCheck = additionalCheck
    }

    private func free<T>(coordinator: BaseCoordinator<T>) {
        childCoordinators[coordinator.id] = nil
        additionalCheck(coordinator)
    }

    func removeAllChildren() {
        for (key, child) in childCoordinators where child.isSelfSustained {
            additionalCheck(child)
            childCoordinators.remove(key)
        }
    }

    @discardableResult
    func coordinate<T>(to coordinator: BaseCoordinator<T>, onStart: () -> Void = {}) -> AnyPublisher<T, Never> {
        guard isSelfSustained else { return Empty().eraseToAnyPublisher() }
        store(coordinator: coordinator)

        let publisher = coordinator.start().first()

        publisher.sink { [weak self, weak coordinator] _ in
            guard let coordinator = coordinator else { return }

            self?.free(coordinator: coordinator)

        }.store(in: &cancellables)

        onStart()
        return publisher.eraseToAnyPublisher()
    }

    func start() -> AnyPublisher<ResultType, Never> {
        fatalError("Start method should be implemented.")
    }

    func canHandleLink(_ code: String) -> Bool {
        false
    }

    func handleLink(_ code: String) {

    }
}
