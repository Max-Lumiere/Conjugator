//
//  Created by Maksim Sviatlou on 7.01.23.

import UIKit
import LumiereToolkit

extension VerbInput.Coordinator {

    convenience init(context: AppContext, parentController: UINavigationController) {
        self.init(viewModelCreator: Creator { .init() },
                  parentController: parentController,
                  verbOutputCoordinatorCreator: Creator { args in
//            VerbOutput.Coordinator(
        })
    }
}
