//
// This file is part of Conjugator.

// Conjugator is free software:
// you can redistribute it and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation,
// either version 3 of the License,
// or (at your option) any later version.
// Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

// See the GNU General Public License for more details.
// You should have received a copy of the GNU General Public License along with Conjugator.
// If not, see <https://www.gnu.org/licenses/>.
//
//
//  Created by Maksim Sviatlou on 7.01.23.

import shared

final class AppContext {

    let conjugationsService: ConjugationsService
    let tenseLocalizationService = LithuanianTenseLocalizationService()

    let aTypeService: VerbFormsService = ATypeFormsService()
    let reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()

    let iTypeService: VerbFormsService = ITypeFormsService()
    let reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()

    let oTypeService: VerbFormsService = OTypeFormsService()
    let reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()
    
    let eTypeService: VerbFormsService = ETypeFormsService()
    let reflexiveETypeService: VerbFormsService = ReflexiveETypeFormsService()

    let futureFormsService: VerbFormsService = FutureFormsService()
    let reflexiveFutureFormsService: VerbFormsService

    let imperativeFormsService: VerbFormsService = ImperativeFormsService()
    let reflexiveImperativeFormsService: VerbFormsService

    let conditionalFormsService: VerbFormsService = ConditionalFormsService()
    let reflexiveConditionalFormsService: VerbFormsService

    let presentConjugationsService: ConjugationsService
    let pastConjugationsService: ConjugationsService
    let pastContiniousConjugationsService: ConjugationsService
    let futureConjugationsService: ConjugationsService
    let imperativeConjugationsService: ConjugationsService
    let conditionalConjugationsService: ConjugationsService

    init() {
        reflexiveFutureFormsService = ReflexiveFutureFormsService(commonFormsService: futureFormsService)
        reflexiveImperativeFormsService = ReflexiveImperativeFormsService(commonFormsService: imperativeFormsService)
        reflexiveConditionalFormsService = ReflexiveConditionalFormsService(commonFormsService: conditionalFormsService)

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

        futureConjugationsService = FutureConjugationsService(commonFormsService: futureFormsService,
                                                              reflexiveFormsService: reflexiveFutureFormsService,
                                                              butiFormsService: ButiFutureVerbFormsService())

        imperativeConjugationsService = ImperativeConjugationsServiceDecorator(inner: SimpleConjugationsService(
            tense: .imperative,
            commonFormsService: imperativeFormsService,
            reflexiveFormsService: reflexiveImperativeFormsService
        ))

        conditionalConjugationsService = SimpleConjugationsService(
            tense: .conditional,
            commonFormsService: conditionalFormsService,
            reflexiveFormsService: reflexiveConditionalFormsService
        )

        conjugationsService = CollectionConjugationsService(innerServices: [presentConjugationsService,
                                                                            pastConjugationsService,
                                                                            pastContiniousConjugationsService,
                                                                            futureConjugationsService,
                                                                            imperativeConjugationsService,
                                                                            conditionalConjugationsService])

    }

}
