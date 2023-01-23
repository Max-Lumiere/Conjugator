//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjunctionsService: ConjunctionsService
    let tenseLocalizationService = LithuanianTenseLocalizationService()

    let aTypeService: VerbFormsService = ATypeFormsService()
    let iTypeService: VerbFormsService = ITypeFormsService()
    let oTypeService: VerbFormsService = OTypeFormsService()
    let reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()
    let reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()
    let reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()

    init() {
        conjunctionsService = CollectionConjunctionsService(innerServices: [
            PresentConjunctionsService(
                aTypeService: aTypeService,
                iTypeService: iTypeService,
                oTypeService: oTypeService,
                reflexiveATypeService: reflexiveATypeService,
                reflexiveITypeService: reflexiveITypeService,
                reflexiveOTypeService: reflexiveOTypeService,
                butiService: ButiPresentVerbFormsService()
            )
        ])
    }

}
