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

class ATypeFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(1) + "u"

        if (form.endsWith("žia")) {
            forms[1] = form.dropLast(3) + "i"
        } else if (form.endsWith("ia")) {
            forms[1] = form.dropLast(1)
        } else {
            forms[1] = form.dropLast(1) + "i"
        }

        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}