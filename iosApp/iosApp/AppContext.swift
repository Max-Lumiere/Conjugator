//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjugationsService: ConjugationsService
    let tenseLocalizationService = LithuanianTenseLocalizationService()

    let aTypeService: VerbFormsService = ATypeFormsService()
    let iTypeService: VerbFormsService = ITypeFormsService()
    let oTypeService: VerbFormsService = OTypeFormsService()
    let eTypeService: VerbFormsService = ETypeFormsService()
    let reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()
    let reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()
    let reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()
    let reflexiveETypeService: VerbFormsService = ReflexiveETypeFormsService()

    let presentConjugationsService: ConjugationsService
    let pastConjugationsService: ConjugationsService
    let pastContiniousConjugationsService: ConjugationsService

    init() {
        presentConjugationsService = PresentConjugationsService(aTypeService: aTypeService,
                                                                iTypeService: iTypeService,
                                                                oTypeService: oTypeService,
                                                                reflexiveATypeService: reflexiveATypeService,
                                                                reflexiveITypeService: reflexiveITypeService,
                                                                reflexiveOTypeService: reflexiveOTypeService,
                                                                butiService: ButiPresentVerbFormsService())
        pastConjugationsService = PastConjugationsService(oTypeService: oTypeService,
                                                          eTypeService: eTypeService,
                                                          reflexiveOTypeService: reflexiveOTypeService,
                                                          reflexiveETypeService: reflexiveETypeService)
        pastContiniousConjugationsService = PastContiniousConjugationsService(
            oTypeService: oTypeService,
            reflexiveOTypeService: reflexiveOTypeService
        )
        conjugationsService = CollectionConjugationsService(innerServices: [
            presentConjugationsService,
            pastConjugationsService,
            pastContiniousConjugationsService
        ])
    }

}
