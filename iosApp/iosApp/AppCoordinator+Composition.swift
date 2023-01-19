//
//  Created by Maksim Sviatlou on 19.01.23.

import UIKit
import LumiereToolkit

extension AppCoordinator {

    convenience init(context: AppContext, window: UIWindow) {
        self.init(window: window,
                  verbCoordinatorCreator: Creator { controller in
            VerbInput.Coordinator(context: context, parentController: controller)
        })
    }
}
