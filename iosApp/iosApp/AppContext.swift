//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjunctionsService: ConjunctionsService
    let tenseLocalizationService = LithuanianTenseLocalizationService()

    let aTypeService: VerbFormsService = ATypeFormsService()
    let iTypeService: VerbFormsService = ITypeFormsService()
    let oTypeService: VerbFormsService = OTypeFormsService()
    let eTypeService: VerbFormsService = ETypeFormsService()
    let reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()
    let reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()
    let reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()
    let reflexiveETypeService: VerbFormsService = ReflexiveETypeFormsService()

    let presentConjunctionsService: ConjunctionsService
    let pastConjunctionsService: ConjunctionsService
    let pastContiniousConjunctionsService: ConjunctionsService

    init() {
        presentConjunctionsService = PresentConjunctionsService(aTypeService: aTypeService,
                                                                iTypeService: iTypeService,
                                                                oTypeService: oTypeService,
                                                                reflexiveATypeService: reflexiveATypeService,
                                                                reflexiveITypeService: reflexiveITypeService,
                                                                reflexiveOTypeService: reflexiveOTypeService,
                                                                butiService: ButiPresentVerbFormsService())
        pastConjunctionsService = PastConjunctionsService(oTypeService: oTypeService,
                                                          eTypeService: eTypeService,
                                                          reflexiveOTypeService: reflexiveOTypeService,
                                                          reflexiveETypeService: reflexiveETypeService)
        pastContiniousConjunctionsService = PastContiniousConjunctionsService(
            oTypeService: oTypeService,
            reflexiveOTypeService: reflexiveOTypeService
        )
        conjunctionsService = CollectionConjunctionsService(innerServices: [
            presentConjunctionsService,
            pastConjunctionsService,
            pastContiniousConjunctionsService
        ])
    }

}
