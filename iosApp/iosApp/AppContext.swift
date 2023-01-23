//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjunctionsService: ConjunctionsService
    let tenseLocalizationService = LithuanianTenseLocalizationService()

    let aTypeService: VerbFormsService = FirstPresentVerbFormsService()
    let iTypeService: VerbFormsService = SecondPresentVerbFormsService()
    let oTypeService: VerbFormsService = SecondPresentVerbFormsService()
    let reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()
    let reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()
    let reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()

    init() {
        conjunctionsService = CollectionConjunctionsService(innerServices: [
            PresentConjunctionsService(verbFormsService: PresentVerbFormsService(
                firstTypeService: aTypeService,
                secondTypeService: iTypeService,
                thirdTypeService: oTypeService,
                reflexiveATypeService: reflexiveATypeService,
                reflexiveITypeService: reflexiveITypeService,
                reflexiveOTypeService: reflexiveOTypeService,
                butiService: ButiPresentVerbFormsService()))
        ])
    }

}
