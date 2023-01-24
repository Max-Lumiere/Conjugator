//
//  Created by Maksim Sviatlou on 19.01.23.

import UIKit
import shared
import LumiereToolkit

extension VerbOutput.Coordinator {

    convenience init(context: AppContext, navigationController: UINavigationController, verb: Verb) {
        self.init(navigationController: navigationController, viewModelCreator: Creator {
            VerbOutput.ViewModel(conjugationsService: context.conjugationsService,
                                 tenseLocalizationService: context.tenseLocalizationService,
                                 verb: verb)
        })
    }

}
