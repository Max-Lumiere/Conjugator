//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjunctionsService: ConjunctionsService
    let aTypeService: VerbFormsService = FirstPresentVerbFormsService()
    let iTypeService: VerbFormsService = SecondPresentVerbFormsService()
    let oTypeService: VerbFormsService = SecondPresentVerbFormsService()

    init() {
        conjunctionsService = CollectionConjunctionsService(innerServices: [
            PresentConjunctionsService(verbFormsService: PresentVerbFormsService(
                firstTypeService: aTypeService,
                secondTypeService: iTypeService,
                thirdTypeService: oTypeService,
                butiService: ButiPresentVerbFormsService()))
        ])
    }

}
