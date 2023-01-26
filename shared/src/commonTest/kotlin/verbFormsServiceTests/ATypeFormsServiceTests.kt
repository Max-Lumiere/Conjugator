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

import verbFormsService.ATypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class ATypeFormsServiceTests: VerbFormsServiceTests() {

    private fun prepare() {
        sut = ATypeFormsService()
    }

    @Test
    fun testGetConjugation_a() {
        prepare()

        val verb = Verb("duoti", "duoda", "davė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "duodu")
        assertEquals(forms[1], "duodi")
        assertEquals(forms[2], "duoda")
        assertEquals(forms[3], "duodame")
        assertEquals(forms[4], "duodate")
        assertEquals(forms[5], "duoda")
    }

    @Test
    fun testGetConjugation_ia() {
        prepare()

        val verb = Verb("veikti", "veikia", "veikė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "veikiu")
        assertEquals(forms[1], "veiki")
        assertEquals(forms[2], "veikia")
        assertEquals(forms[3], "veikiame")
        assertEquals(forms[4], "veikiate")
        assertEquals(forms[5], "veikia")
    }
}