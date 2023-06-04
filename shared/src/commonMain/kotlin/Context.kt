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
import stringsProvider.StringsProvider
import verbFormsService.*

open class Context {

    val conjugationsService: ConjugationsService
    val tenseLocalizationService: TenseLocalizationService = EnglishTenseLocalizationService()

    val aTypeService: VerbFormsService = ATypeFormsService()
    val reflexiveATypeService: VerbFormsService = ReflexiveATypeFormsService()

    val iTypeService: VerbFormsService = ITypeFormsService()
    val reflexiveITypeService: VerbFormsService = ReflexiveITypeFormsService()

    val oTypeService: VerbFormsService = OTypeFormsService()
    val reflexiveOTypeService: VerbFormsService = ReflexiveOTypeFormsService()

    val eTypeService: VerbFormsService = ETypeFormsService()
    val reflexiveETypeService: VerbFormsService = ReflexiveETypeFormsService()

    val futureFormsService: VerbFormsService = FutureFormsService()
    val reflexiveFutureFormsService: VerbFormsService

    val imperativeFormsService: VerbFormsService = ImperativeFormsService()
    val reflexiveImperativeFormsService: VerbFormsService

    val conditionalFormsService: VerbFormsService = ConditionalFormsService()
    val reflexiveConditionalFormsService: VerbFormsService

    val presentConjugationsService: ConjugationsService
    val pastConjugationsService: ConjugationsService
    val pastContiniousConjugationsService: ConjugationsService
    val futureConjugationsService: ConjugationsService
    val imperativeConjugationsService: ConjugationsService
    val conditionalConjugationsService: ConjugationsService

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
            ),
            butiFormsService = ButiImperativeVerbFormsService()
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