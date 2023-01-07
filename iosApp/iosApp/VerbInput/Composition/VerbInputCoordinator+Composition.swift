//
//  VerbInputCoordinator+Composition.swift
//  iosApp
//
//  Created by Max Svetlov on 7.01.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

extension VerbInput.Coordinator {

    convenience init(context: AppContext, parentController: UIViewController) {
        self.init(viewModelCreator: Creator { .init() },
                  parentController: parentController)
    }
}
