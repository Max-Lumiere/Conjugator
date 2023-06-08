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

import entities.Verb
import kotlinx.serialization.Serializable

 @Serializable
 data class VerbJSON(val infinitive: String, val present: String, val past: String) {

     constructor(verb: Verb) : this(verb.infinitive, verb.present, verb.past)
 }

fun Verb.Companion.fromJSON(verbJSON: VerbJSON) : Verb {
    return Verb(verbJSON.infinitive, verbJSON.present, verbJSON.past)
}