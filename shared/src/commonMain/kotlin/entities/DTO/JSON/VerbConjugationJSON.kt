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

package entities.DTO.JSON

import VerbJSON
import entities.Tense
import entities.Verb
import entities.VerbConjugation
import fromJSON
import kotlinx.serialization.Serializable

@Serializable
class VerbConjugationJSON(val verb: VerbJSON,
                          val tense: TenseJSON,
                          val forms: List<String>) {

    constructor(conjugation: VerbConjugation) : this(
        verb = VerbJSON(conjugation.verb),
        tense = TenseJSON.jsonFrom(conjugation.tense),
        forms = conjugation.forms
    )
}

fun VerbConjugation.Companion.fromJSON(json: VerbConjugationJSON) : VerbConjugation {
    return VerbConjugation(
        verb = Verb.fromJSON(json.verb),
        tense = Tense.fromJSON(json.tense),
        forms = json.forms
    )
}
