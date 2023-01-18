//
//  Created by Maksim Sviatlou on 7.01.23.

import UIKit
import Combine
import shared

final class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    private var context: AppContext?
    private var cancellables = Set<AnyCancellable>()
    private let tabController = UITabBarController()
    private let navigationController = UINavigationController()
    private var coordinator: AnyObject?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        let window = UIWindow(windowScene: windowScene)
        let context = AppContext()
        let coordinator = VerbInput.Coordinator(context: context, parentController: navigationController)

        tabController.setViewControllers([navigationController], animated: false)
        window.rootViewController = tabController

        self.coordinator = coordinator
        self.window = window
        self.context = context
        window.makeKeyAndVisible()

        coordinator
            .start()
            .sink { _ in }
            .store(in: &cancellables)
    }

}
