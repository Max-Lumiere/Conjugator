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

import verbFormsService.ITypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class ITypeFormsServiceTests: VerbFormsServiceTests() {

    private fun prepare() {
        sut = ITypeFormsService()
    }

    @Test
    fun testGetConjugation_i() {
        prepare()

        val verb = Verb("tylėti", "tyli", "tyjėjo")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "tyliu")
        assertEquals(forms[1], "tyli")
        assertEquals(forms[2], "tyli")
        assertEquals(forms[3], "tylime")
        assertEquals(forms[4], "tylite")
        assertEquals(forms[5], "tyli")
    }
}