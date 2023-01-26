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

package conjugationsServiceTests

import conjugationsService.PastConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PastConjugationsServiceTests: ConjugationsServiceTests() {

    private var eType = VerbFormsServiceMock()
    private var oType = VerbFormsServiceMock()
    private var reflexiveEType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        eType = VerbFormsServiceMock()
        oType = VerbFormsServiceMock()
        reflexiveEType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        sut = PastConjugationsService(
            oType,
            eType,
            reflexiveOType,
            reflexiveEType
        )
    }

    @Test
    fun test_past_conjunctions() = runTest {
        val verb = Verb("","","buvo")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.Past)
    }

    @Test
    fun testGetConjugation_o_type() = runTest {
        val verb = Verb("", "", "buvo")

        sut!!.getConjugationsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 1)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_e_type() = runTest {
        val verb = Verb("", "", "darė")

        sut!!.getConjugationsFor(verb)

        assertTrue(eType.getFormsCount == 1)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_e() = runTest {
        val verb = Verb("", "", "darėsi")

        sut!!.getConjugationsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 1)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() = runTest {
        val verb = Verb("", "", "dainavosi")

        sut!!.getConjugationsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 1)
    }

    @Test
    fun testGetConjugation_si_error() = runTest {
        val verb = Verb("", "", "mokzxsi")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_error() = runTest {
        val verb = Verb("", "", "mokzx")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

}