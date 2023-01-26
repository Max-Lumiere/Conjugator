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

class FutureFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        val base = when (form.dropLast(2).last()) {
            's' -> form.dropLast(2)
            'š' -> form.dropLast(2)
            'z' -> form.dropLast(3) + 's'
            'ž' -> form.dropLast(3) + 'š'
            else -> form.dropLast(2) + 's'
        }
        forms[0] = base + "iu"
        forms[1] = base + "i"
        forms[2] = base
        forms[3] = base + "ime"
        forms[4] = base + "ite"
        forms[5] = base

        return forms
    }
}