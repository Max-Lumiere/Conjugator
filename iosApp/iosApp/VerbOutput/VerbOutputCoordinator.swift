//
//  Created by Maksim Sviatlou on 18.01.23.

import LumiereToolkit
import Combine

extension VerbOutput {

    final class Coordinator: BaseCoordinator<Void> {
        private let viewModelCreator: Creator<Void, ViewModel>
        private var viewModel: ViewModel?
        private var controller: VerbOutputViewController?
        private var cancellables = Set<AnyCancellable>()

        init(viewModelCreator: Creator<Void, ViewModel>) {
            self.viewModelCreator = viewModelCreator
        }

        override func start() -> AnyPublisher<Void, Never> {
            let controller = VerbOutputViewController(nibName: nil, bundle: nil)
            let viewModel = viewModelCreator.create()

            self.viewModel = viewModel
            self.controller = controller
            return viewModel.onClose
        }

        private func bind() {
            guard let viewModel = viewModel, let controller = controller else { return }

            controller.onClose
                .sink { [weak viewModel] in viewModel?.close() }
                .store(in: &cancellables)
        }
    }

}
