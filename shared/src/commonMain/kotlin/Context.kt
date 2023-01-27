/*
 * This file is part of Conjugator.
 *
 * Conjugator is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation,
 * either version 3 of the License,
 * or (at your option) any later version.
 * Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Conjugator.
 * If not, see <https://www.gnu.org/licenses/>.
 */

import conjugationsService.*
import entities.Tense
import localization.*
import verbFormsService.*

open class Context {

    open val conjugationsService: ConjugationsService
    open val tenseLocalizationService: TenseLocalizationService
            = LithuanianTenseLocalizationService()

    open val aTypeService: VerbFormsService = ATypeFormsService()
    open val reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()

    open val iTypeService: VerbFormsService = ITypeFormsService()
    open val reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()

    open val oTypeService: VerbFormsService = OTypeFormsService()
    open val reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()

    open val eTypeService: VerbFormsService = ETypeFormsService()
    open val reflexiveETypeService: VerbFormsService = ReflexiveETypeFormsService()

    open val futureFormsService: VerbFormsService = FutureFormsService()
    open val reflexiveFutureFormsService: VerbFormsService

    open val imperativeFormsService: VerbFormsService = ImperativeFormsService()
    open val reflexiveImperativeFormsService: VerbFormsService

    open val conditionalFormsService: VerbFormsService = ConditionalFormsService()
    open val reflexiveConditionalFormsService: VerbFormsService

    open val presentConjugationsService: ConjugationsService
    open val pastConjugationsService: ConjugationsService
    open val pastContiniousConjugationsService: ConjugationsService
    open val futureConjugationsService: ConjugationsService
    open val imperativeConjugationsService: ConjugationsService
    open val conditionalConjugationsService: ConjugationsService

    init {
        reflexiveFutureFormsService = ReflexiveFutureFormsService(
            commonFormsService = futureFormsService
        )
        reflexiveImperativeFormsService = ReflexiveImperativeFormsService(
            commonFormsService = imperativeFormsService
        )
        reflexiveConditionalFormsService = ReflexiveConditionalFormsService(
            commonFormsService = conditionalFormsService
        )
        presentConjugationsService = PresentConjugationsService(
            aTypeService = aTypeService,
            iTypeService = iTypeService,
            oTypeService = oTypeService,
            reflexiveATypeService = reflexiveATypeService,
            reflexiveITypeService = reflexiveITypeService,
            reflexiveOTypeService = reflexiveOTypeService,
            butiService = ButiPresentVerbFormsService()
        )
        pastConjugationsService = PastConjugationsService(
            oTypeService = oTypeService,
            eTypeService = eTypeService,
            reflexiveOTypeService = reflexiveOTypeService,
            reflexiveETypeService = reflexiveETypeService
        )
        pastContiniousConjugationsService = PastContiniousConjugationsService(
            oTypeService = oTypeService,
            reflexiveOTypeService = reflexiveOTypeService
        )
        futureConjugationsService = FutureConjugationsService(
            commonFormsService = futureFormsService,
            reflexiveFormsService = reflexiveFutureFormsService,
            butiFormsService = ButiFutureVerbFormsService()
        )
        imperativeConjugationsService = ImperativeConjugationsServiceDecorator(
            inner = SimpleConjugationsService(
                tense = Tense.Imperative,
                commonFormsService = imperativeFormsService,
                reflexiveFormsService = reflexiveImperativeFormsService
            )
        )
        conditionalConjugationsService = SimpleConjugationsService(
            tense = Tense.Conditional,
            commonFormsService = conditionalFormsService,
            reflexiveFormsService = reflexiveConditionalFormsService
        )
        conjugationsService = CollectionConjugationsService(innerServices = listOf(
            presentConjugationsService,
            pastConjugationsService,
            pastContiniousConjugationsService,
            futureConjugationsService,
            imperativeConjugationsService,
            conditionalConjugationsService)
        )
    }
}