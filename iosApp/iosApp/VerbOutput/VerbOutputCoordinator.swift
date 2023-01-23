//
//  Created by Maksim Sviatlou on 18.01.23.

import LumiereToolkit
import Combine

extension VerbOutput {

    final class Coordinator: BaseCoordinator<Void> {
        private let viewModelCreator: Creator<Void, ViewModel>
        private var viewModel: ViewModel?
        private let navigationController: UINavigationController
        private var controller: VerbOutputViewController?
        private var cancellables = Set<AnyCancellable>()

        init(navigationController: UINavigationController, viewModelCreator: Creator<Void, ViewModel>) {
            self.navigationController = navigationController
            self.viewModelCreator = viewModelCreator
        }

        override func start() -> AnyPublisher<Void, Never> {
            let controller = VerbOutputViewController(nibName: nil, bundle: nil)
            let viewModel = viewModelCreator.create()

            self.viewModel = viewModel
            self.controller = controller

            navigationController.pushViewController(controller, animated: true)
            bind()
            return viewModel.onClose
        }

        private func bind() {
            guard let viewModel = viewModel, let controller = controller else { return }

            controller.onViewDidLoad
                .sink { [weak viewModel] in viewModel?.conjure() }
                .store(in: &cancellables)

            controller.onClose
                .sink { [weak viewModel] in viewModel?.close() }
                .store(in: &cancellables)

            viewModel.onConjunctions
                .sink { [weak controller] in controller?.set(conjunctions: $0) }
                .store(in: &cancellables)
        }
    }

}
