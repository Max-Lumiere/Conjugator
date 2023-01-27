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

class ConditionalFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(2) + "čiau"
        forms[1] = form.dropLast(2) + "tum"
        forms[2] = form.dropLast(2) + "tų"
        forms[3] = form.dropLast(2) + "tume / " + form.dropLast(2) + "tumėme"
        forms[4] = form.dropLast(2) + "tute / " + form.dropLast(2) + "tumėte"
        forms[5] = form.dropLast(2) + "tų"

        return forms
    }
}