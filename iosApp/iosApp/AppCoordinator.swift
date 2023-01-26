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
