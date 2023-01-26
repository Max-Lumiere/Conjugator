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

import conjugationsService.SimpleConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleConjugationsServiceTests: ConjugationsServiceTests() {

    private var commonType = VerbFormsServiceMock()
    private var reflexiveType = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        commonType = VerbFormsServiceMock()
        reflexiveType = VerbFormsServiceMock()
        sut = SimpleConjugationsService(Tense.Imperative, commonType, reflexiveType)
    }

    @Test
    fun test_imperative_conjugations() = runTest {
        val verb = Verb("buti","","")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.Imperative)
    }

    @Test
    fun test_reflexive_conjugation() = runTest {
        val verb = Verb("darytis", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(reflexiveType.getFormsCount == 1)
        assertTrue(commonType.getFormsCount == 0)
    }

    @Test
    fun test_common_conjugation() = runTest {
        val verb = Verb("daryti", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(reflexiveType.getFormsCount == 0)
        assertTrue(commonType.getFormsCount == 1)
    }

    @Test
    fun test_conjugation_error() = runTest {
        val verb = Verb("mokzx", "", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(commonType.getFormsCount == 0)
        assertTrue(reflexiveType.getFormsCount == 0)
    }
}