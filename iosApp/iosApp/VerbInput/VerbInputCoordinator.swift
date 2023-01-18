//
//  VerbInputCoordinator.swift
//  iosApp
//
//  Created by Max Svetlov on 7.01.23.
//  Copyright © 2023 orgName. All rights reserved.
//

//
//  Created by Maksim Sviatlou on 7.01.23.

import Combine
import UIKit
import LumiereToolkit
import shared

extension VerbInput {
    final class Coordinator: BaseCoordinator<Verb> {
        private let viewModelCreator: Creator<Void, ViewModel>
        private let parentController: UIViewController
        private var controller: VerbInputViewController?
        private var viewModel: ViewModel?
        private var cancellables = Set<AnyCancellable>()

        init(viewModelCreator: Creator<Void, ViewModel>, parentController: UIViewController) {
            self.viewModelCreator = viewModelCreator
            self.parentController = parentController
            super.init()
        }

        override func start() -> AnyPublisher<Verb, Never> {
            let viewModel = viewModelCreator.create()
            let controller = VerbInputViewController(nibName: nil, bundle: nil)

            self.viewModel = viewModel
            self.controller = controller

            controller.modalPresentationStyle = .fullScreen
            parentController.present(controller, animated: false)

            bind()

            return viewModel.verbPublisher
        }

        private func bind() {
            guard let controller, let viewModel else { return }

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
