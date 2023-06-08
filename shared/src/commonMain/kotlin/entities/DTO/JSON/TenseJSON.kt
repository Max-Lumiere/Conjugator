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

import entities.Tense
import kotlinx.serialization.Serializable

@Serializable
enum class TenseJSON {
    Present, Past, PastContiniuos, Conditional, Future, Imperative;

    companion object {
        fun jsonFrom(tense: Tense) : TenseJSON {
            return when (tense) {
                Tense.Present -> Present
                Tense.Past -> Past
                Tense.PastContiniuos -> PastContiniuos
                Tense.Conditional -> Conditional
                Tense.Future -> Future
                Tense.Imperative -> Imperative
            }
        }
    }
}

fun Tense.Companion.fromJSON(json: TenseJSON) : Tense {
    return when (json) {
        TenseJSON.Present -> Tense.Present
        TenseJSON.Past -> Tense.Past
        TenseJSON.PastContiniuos -> Tense.PastContiniuos
        TenseJSON.Conditional -> Tense.Conditional
        TenseJSON.Future -> Tense.Future
        TenseJSON.Imperative -> Tense.Imperative
    }
}
