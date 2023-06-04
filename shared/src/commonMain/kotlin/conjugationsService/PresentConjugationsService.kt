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

class PresentConjugationsService(
    private val aTypeService: VerbFormsService,
    private val iTypeService: VerbFormsService,
    private val oTypeService: VerbFormsService,
    private val reflexiveATypeService: VerbFormsService,
    private val reflexiveITypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val butiService: VerbFormsService
    ): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val forms: List<String>

        if (verb.infinitive == "būti" || verb.infinitive == "buti") {
            forms = butiService.getVerbFormsFor(verb.present)
        } else if (verb.present.endsWith("si")) {
            forms = when (verb.present.dropLast(2).last()) {
                'a' -> reflexiveATypeService.getVerbFormsFor(verb.present)
                'i' -> reflexiveITypeService.getVerbFormsFor(verb.present)
                'o' -> reflexiveOTypeService.getVerbFormsFor(verb.present)
                else -> {
                    throw Exception("Wrong present form ${verb.present}")
                }
            }
        } else {
            forms = when (verb.present.last()) {
                'a' -> aTypeService.getVerbFormsFor(verb.present)
                'i' -> iTypeService.getVerbFormsFor(verb.present)
                'o' -> oTypeService.getVerbFormsFor(verb.present)
                else -> {
                    throw Exception("Wrong present form ${verb.present}")
                }
            }
        }

        return listOf(VerbConjugation(verb, Tense.Present, forms))
    }

}