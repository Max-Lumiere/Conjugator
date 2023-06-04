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

package verbFormsService

class ButiPresentVerbFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = "esu / būnu"
        forms[1] = "esi / būni"
        forms[2] = "yra / būna"
        forms[3] = "esame / būname"
        forms[4] = "esate / būnate"
        forms[5] = "yra / būna"

        return forms
    }
}