//
// This file is part of Conjugator.

// Conjugator is free software:
// you can redistribute it and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation,
// either version 3 of the License,
// or (at your option) any later version.
// Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

// See the GNU General Public License for more details.
// You should have received a copy of the GNU General Public License along with Conjugator.
// If not, see <https://www.gnu.org/licenses/>.
//
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

    func scene(_ scene: UIScene,
               willConnectTo session: UISceneSession,
               options connectionOptions: UIScene.ConnectionOptions) {
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
