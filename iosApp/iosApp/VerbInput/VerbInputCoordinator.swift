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

import Combine
import UIKit
import LumiereToolkit
import shared

extension VerbInput {
    final class Coordinator: BaseCoordinator<Void> {
        private let viewModelCreator: Creator<Void, ViewModel>
        private let parentController: UINavigationController
        private var controller: VerbInputViewController?
        private var viewModel: ViewModel?
        private var cancellables = Set<AnyCancellable>()
        private var verbOutputCoordinator: BaseCoordinator<Void>?
        private let verbOutputCoordinatorCreator: Creator<Verb, BaseCoordinator<Void>>
        private let animated: Bool
        private let analytics: Analytics

        init(viewModelCreator: Creator<Void, ViewModel>,
             parentController: UINavigationController,
             animated: Bool = false,
             verbOutputCoordinatorCreator: Creator<Verb, BaseCoordinator<Void>>,
             analytics: Analytics) {
            self.viewModelCreator = viewModelCreator
            self.parentController = parentController
            self.animated = animated
            self.verbOutputCoordinatorCreator = verbOutputCoordinatorCreator
            self.analytics = analytics
            super.init()
        }

        override func start() -> AnyPublisher<Void, Never> {
            let viewModel = viewModelCreator.create()
            let controller = VerbInputViewController(nibName: nil, bundle: nil)

            self.viewModel = viewModel
            self.controller = controller

            _ = controller.view
            parentController.setViewControllers([controller], animated: animated)

            bind()

            return controller.onClose
        }

        private func bind() {
            guard let controller, let viewModel else { return }

            viewModel.verbPublisher.sink { [weak self] verb in
                guard let self = self else { return }

                self.analytics.track(ConjugateEvent(verb: verb))
                let coordinator = self.verbOutputCoordinatorCreator.create(with: verb)

                self.coordinate(to: coordinator)
            }.store(in: &cancellables)

            controller.infivitivePublisher.sink { [weak viewModel] in
                viewModel?.set(infinitive: $0)
            }.store(in: &cancellables)

            controller.presentPublisher.sink { [weak viewModel] in
                viewModel?.set(present: $0)
            }.store(in: &cancellables)

            controller.pastPublisher.sink { [weak viewModel] in
                viewModel?.set(past: $0)
            }.store(in: &cancellables)

            viewModel.infinitivePublisher.sink { [weak controller] in
                controller?.set(infinitive: $0)
            }.store(in: &cancellables)

            viewModel.presentPublisher.sink { [weak controller] in
                controller?.set(present: $0)
            }.store(in: &cancellables)

            viewModel.pastPublisher.sink { [weak controller] in
                controller?.set(past: $0)
            }.store(in: &cancellables)

            controller.conjurePublisher.sink { [weak viewModel] in
                viewModel?.conjure()
            }.store(in: &cancellables)

            viewModel.infinitiveActivationPublisher.sink { [weak controller] in
                controller?.setInfinitiveTextField(active: $0)
            }.store(in: &cancellables)

            viewModel.presentActivationPublisher.sink { [weak controller] in
                controller?.setPresentTextField(active: $0)
            }.store(in: &cancellables)

            viewModel.pastActivationPublisher.sink { [weak controller] in
                controller?.setPastTextField(active: $0)
            }.store(in: &cancellables)

            viewModel.infinitiveFailurePublisher.sink { [weak controller] in
                controller?.setInfinitiveFailureHighlight(active: $0)
            }.store(in: &cancellables)

            viewModel.presentFailurePublisher.sink { [weak controller] in
                controller?.setPresentFailureHighlight(active: $0)
            }.store(in: &cancellables)

            viewModel.pastFailurePublisher.sink { [weak controller] in
                controller?.setPastFailureHighlight(active: $0)
            }.store(in: &cancellables)

            controller.infinitiveReturnPublisher.sink { [weak viewModel] in
                viewModel?.infinitiveReturn()
            }.store(in: &cancellables)

            controller.presentReturnPublisher.sink { [weak viewModel] in
                viewModel?.presentReturn()
            }.store(in: &cancellables)

            controller.pastReturnPublisher.sink { [weak viewModel] in
                viewModel?.pastReturn()
            }.store(in: &cancellables)
        }
    }
}
