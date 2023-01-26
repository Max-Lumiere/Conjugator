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

package verbFormsServiceTests

import verbFormsService.OTypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class OTypeFormsServiceTests: VerbFormsServiceTests() {

    private fun prepare() {
        sut = OTypeFormsService()
    }

    @Test
    fun testGetConjugation_o() {
        prepare()

        val verb = Verb("mokyti", "moko", "mokė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "mokau")
        assertEquals(forms[1], "mokai")
        assertEquals(forms[2], "moko")
        assertEquals(forms[3], "mokome")
        assertEquals(forms[4], "mokote")
        assertEquals(forms[5], "moko")
    }
}