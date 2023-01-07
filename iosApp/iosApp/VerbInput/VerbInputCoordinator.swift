//
//  VerbInputCoordinator.swift
//  iosApp
//
//  Created by Max Svetlov on 7.01.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import UIKit

extension VerbInput {
    final class Coordinator: BaseCoordinator<Void> {
        private let viewModelCreator: Creator<Void, ViewModel>
        private let parentController: UIViewController
        private var controller: VerbInputViewController?
        private var viewModel: ViewModel?

        init(viewModelCreator: Creator<Void, ViewModel>, parentController: UIViewController) {
            self.viewModelCreator = viewModelCreator
            self.parentController = parentController
        }

        override func start() -> AnyPublisher<Void, Never> {
            let viewModel = viewModelCreator.create()
            let controller = VerbInputViewController(nibName: nil, bundle: nil)

            self.viewModel = viewModel
            self.controller = controller

            controller.modalPresentationStyle = .fullScreen
            parentController.present(controller, animated: true)

            return Empty(completeImmediately: false).eraseToAnyPublisher()
        }
    }
}
