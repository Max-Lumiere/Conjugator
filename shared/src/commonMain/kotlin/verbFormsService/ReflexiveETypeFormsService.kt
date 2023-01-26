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

class ReflexiveETypeFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        if (form.dropLast(3).last() == 't') {
            forms[0] = form.dropLast(4) + "čiausi"
        } else {
            forms[0] = form.dropLast(3) + "iausi"
        }
        forms[1] = form.dropLast(3) + "eisi"
        forms[2] = form
        forms[3] = form.dropLast(2) + "mės"
        forms[4] = form.dropLast(2) + "tės"
        forms[5] = form

        return forms
    }
}