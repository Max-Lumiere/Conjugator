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

class PastConjugationsService(
    private val oTypeService: VerbFormsService,
    private val eTypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val reflexiveETypeService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val forms: List<String>

        if (verb.past.endsWith("si")) {
            forms = when (verb.past.dropLast(2).last()) {
                'ė' -> reflexiveETypeService.getVerbFormsFor(verb.past)
                'o' -> reflexiveOTypeService.getVerbFormsFor(verb.past)
                else -> {
                    throw Exception("Wrong past form ${verb.past}")
                }
            }
        } else {
            forms = when (verb.past.last()) {
                'ė' -> eTypeService.getVerbFormsFor(verb.past)
                'o' -> oTypeService.getVerbFormsFor(verb.past)
                else -> {
                    throw Exception("Wrong past form ${verb.past}")
                }
            }
        }

        return listOf(VerbConjugation(verb, Tense.Past, forms))
    }

}