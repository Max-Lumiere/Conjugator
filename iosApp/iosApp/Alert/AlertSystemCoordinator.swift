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
// Created by Maksim Sviatlou on 26.01.23.

import UIKit
import Combine
import LumiereToolkit

extension Alert {

    final class SystemCoordinator: BaseCoordinator<Void> {
        private let presentingController: UIViewController
        private let error: Error
        private let title: String?
        private let message: String?
        private let actions: [UIAlertAction]?
        private let analytics: Analytics

        init(presentingController: UIViewController,
             error: Error,
             analytics: Analytics,
             title: String? = nil,
             message: String? = nil,
             actions: [UIAlertAction]? = nil) {
            self.presentingController = presentingController
            self.error = error
            self.analytics = analytics
            self.title = title
            self.message = message
            self.actions = actions
            super.init()
        }

        override func start() -> AnyPublisher<Void, Never> {
            let controller = UIAlertController(title: title ?? "Error",
                                               message: message ?? "Something went wrong...",
                                               preferredStyle: .alert)
            let closeSubject = PassthroughSubject<Void, Never>()
            let closeAction = UIAlertAction(title: UIKitLocalized.ok, style: .destructive) { _ in
                closeSubject.send(())
            }

            analytics.track(ErrorEvent(error: error, source: presentingController))

            actions?.forEach { action in controller.addAction(action) }
            controller.addAction(closeAction)
            presentingController.present(controller, animated: true)

            return closeSubject.eraseToAnyPublisher()
        }

    }
}
