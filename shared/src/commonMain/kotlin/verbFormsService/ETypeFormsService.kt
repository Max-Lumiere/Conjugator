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

class ETypeFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        if (form.dropLast(1).last() == 'd') {
            forms[0] = form.dropLast(1) + "žiau"
        } else if (form.dropLast(1).last() == 't') {
            forms[0] = form.dropLast(2) + "čiau"
        } else {
            forms[0] = form.dropLast(1) + "iau"
        }
        forms[1] = form.dropLast(1) + "ei"
        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}