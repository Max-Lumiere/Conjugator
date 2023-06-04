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

package entities

import kotlinx.serialization.Serializable

@Serializable
data class Verb(val infinitive: String, val present: String, val past: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Verb

        if (infinitive != other.infinitive) return false
        if (present != other.present) return false
        if (past != other.past) return false

        return true
    }

    override fun hashCode(): Int {
        var result = infinitive.hashCode()

        result = 31 * result + present.hashCode()
        result = 31 * result + past.hashCode()
        return result
    }
}