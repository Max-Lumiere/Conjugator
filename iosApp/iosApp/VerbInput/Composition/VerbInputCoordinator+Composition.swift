//
//  Created by Maksim Sviatlou on 7.01.23.

import UIKit
import LumiereToolkit

extension VerbInput.Coordinator {

    convenience init(context: AppContext, parentController: UINavigationController) {
        self.init(viewModelCreator: Creator { .init() },
                  parentController: parentController,
                  verbOutputCoordinatorCreator: Creator { verb in
            VerbOutput.Coordinator(context: context, navigationController: parentController, verb: verb)
        })
    }
}
