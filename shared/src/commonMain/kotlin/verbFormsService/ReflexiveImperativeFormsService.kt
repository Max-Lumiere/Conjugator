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

class ReflexiveImperativeFormsService(
    private val commonFormsService: VerbFormsService
    ): VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = commonFormsService.getVerbFormsFor(form.dropLast(1)).toMutableList()

        forms[1] += "is"
        forms[3] = forms[3].dropLast(1) + "ės"
        forms[4] = forms[4].dropLast(1) + "ės"

        return forms
    }
}