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

import conjugationsService.PastContiniousConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PastContiniousConjugationsServiceTests: ConjugationsServiceTests() {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return emptyList()
        }
    }

    private var oType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        oType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        sut = PastContiniousConjugationsService(oType, reflexiveOType)
    }

    @Test
    fun test_past_continious_conjunctions() = runTest {
        val verb = Verb("buti","","")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.PastContiniuos)
    }

    @Test
    fun testGetConjugation() = runTest {
        val verb = Verb("būti", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(oType.getFormsCount == 1)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() = runTest {
        val verb = Verb("darytis", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 1)
    }

    @Test
    fun testGetConjugation_error() = runTest {
        val verb = Verb("mokzx", "", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }
}