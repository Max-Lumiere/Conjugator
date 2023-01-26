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

class SimpleConjugationsService(
    private val tense: Tense,
    private val commonFormsService: VerbFormsService,
    private val reflexiveFormsService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val form = verb.infinitive

        val forms = if (form.endsWith("tis")) {
            reflexiveFormsService.getVerbFormsFor(form)
        } else if (form.endsWith("ti")) {
            commonFormsService.getVerbFormsFor(form)
        } else {
            throw Exception("Wrong infinitive ${verb.infinitive}")
        }

        return listOf(VerbConjugation(verb, tense, forms))
    }

}