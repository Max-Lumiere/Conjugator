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

class ReflexiveConditionalFormsService(
    private val commonFormsService: VerbFormsService
): VerbFormsService {
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = commonFormsService.getVerbFormsFor(form.dropLast(1)).toMutableList()

        forms[0] += "si"
        forms[1] += "eisi"
        forms[2] += "si"
        forms[3] = transformComplexForm(forms[3])
        forms[4] = transformComplexForm(forms[4])
        forms[5] += "si"

        return forms
    }

    private fun transformComplexForm(form: String): String {
        return form
            .replace(" ","")
            .split('/')
            .map {  it.dropLast(1) + "ės" }
            .joinToString(" / ")
    }
}