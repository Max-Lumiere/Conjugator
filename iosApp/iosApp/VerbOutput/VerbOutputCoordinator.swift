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

            navigationController.setUpSwipeBackSupport().store(in: &cancellables)
            navigationController.pushViewController(controller, animated: true)
            bind()
            return viewModel.onClose
        }

        private func bind() {
            guard let viewModel = viewModel, let controller = controller else { return }

            controller.onViewDidLoad
                .sink { [weak viewModel] in viewModel?.conjure() }
                .store(in: &cancellables)

            controller.onBack
                .sink { [weak self] in self?.navigationController.popViewController(animated: true) }
                .store(in: &cancellables)

            controller.onClose
                .sink { [weak viewModel] in viewModel?.close() }
                .store(in: &cancellables)

            viewModel.onItems
                .sink { [weak controller] in controller?.set(items: $0) }
                .store(in: &cancellables)
        }
    }

}
