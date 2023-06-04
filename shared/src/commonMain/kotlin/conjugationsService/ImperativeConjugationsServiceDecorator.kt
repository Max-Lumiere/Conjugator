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

package conjugationsService

import entities.Tense
import entities.Verb
import entities.VerbConjugation
import verbFormsService.VerbFormsService

class ImperativeConjugationsServiceDecorator(
    inner: ConjugationsService,
    private val butiFormsService: VerbFormsService
) : ConjugationsServiceDecorator(inner) {

    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val conjugations = super.getConjugationsFor(verb).toMutableList()
        val index = conjugations.indexOfFirst { it.tense == Tense.Imperative }

        if (index != -1) {
            var forms = conjugations[index].forms.toMutableList()

            if (verb.infinitive == "būti" || verb.infinitive == "buti") {
                forms = butiFormsService.getVerbFormsFor("").toMutableList()
            } else {
                val teguForm = if (verb.infinitive.endsWith("tis")) {
                    "tegu " + verb.present + " / tesi" + verb.present.dropLast(2)
                } else if (verb.infinitive.endsWith("ti")) {
                    "tegu " + verb.present
                } else {
                    throw Exception("Wrong infinitive ${verb.infinitive}")
                }

                forms[2] = teguForm
                forms[5] = teguForm
            }

            conjugations[index] = VerbConjugation(
                verb = verb,
                tense = Tense.Imperative,
                forms = forms
            )
        }

        return conjugations
    }

}