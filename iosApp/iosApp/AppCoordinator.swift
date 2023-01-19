//
//  Created by Maksim Sviatlou on 19.01.23.

import LumiereToolkit
import Combine

final class AppCoordinator: BaseCoordinator<Never> {
    private let window: UIWindow
    private let tabController = UITabBarController()
    private let navigationController = UINavigationController()
    private let verbCoordinatorCreator: Creator<UINavigationController, BaseCoordinator<Void>>
    private var verbCoordinator: BaseCoordinator<Void>?
    private var cancellables = Set<AnyCancellable>()

    init(window: UIWindow, verbCoordinatorCreator: Creator<UINavigationController, BaseCoordinator<Void>>) {
        self.window = window
        self.verbCoordinatorCreator = verbCoordinatorCreator
        super.init()
    }

    override func start() -> AnyPublisher<Never, Never> {
        tabController.setViewControllers([navigationController], animated: false)
        window.rootViewController = tabController
        window.makeKeyAndVisible()

        let verbCoordinator = verbCoordinatorCreator.create(with: navigationController)

        coordinate(to: verbCoordinator)
        self.verbCoordinator = verbCoordinator
        return Empty(completeImmediately: false).eraseToAnyPublisher()
    }
}
