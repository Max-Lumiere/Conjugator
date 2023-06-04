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

import conjugationsService.ConjugationsService
import conjugationsService.ImperativeConjugationsServiceDecorator
import entities.Tense
import entities.Verb
import entities.VerbConjugation
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ImperativeConjugationsServiceDecoratorTests: ConjugationsServiceTests() {
    var inner: ConjugationsServiceMock = ConjugationsServiceMock()
    var buti: VerbFormsServiceMock = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        inner = ConjugationsServiceMock()
        buti = VerbFormsServiceMock()
        sut = ImperativeConjugationsServiceDecorator(inner = inner, butiFormsService = buti)
    }

    @Test
    fun test_buti() = runTest {
        var verb = Verb("būti", "būna", "buvo")

        inner.result = listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf("", "", "", "", "", "")
        ))
        sut!!.getConjugationsFor(verb)

        assertEquals(1, buti.getFormsCount)

        verb = Verb("buti", "būna", "buvo")

        sut!!.getConjugationsFor(verb)

        assertEquals(2, buti.getFormsCount)
    }

    @Test
    fun test_imperative_ti() = runTest {
        val verb = Verb("kalbėti", "kalba", "kalbėjo")
        inner.result = listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf(
                "–",
                "kalbėk",
                "–",
                "kalbėkime",
                "kalbėkite",
                "–"
            )
        ))

        assertEquals(listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf(
            "–",
            "kalbėk",
            "tegu kalba",
            "kalbėkime",
            "kalbėkite",
            "tegu kalba"
        ))), sut!!.getConjugationsFor(verb))
        assertEquals(1, inner.getConjugationsCount)
    }

    @Test
    fun test_imperative_tis() = runTest {
        val verb = Verb("mokytis", "mokosi", "mokėsi")
        inner.result = listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf(
                "–",
                "mokykis",
                "–",
                "mokykimės",
                "mokykitės",
                "–"
            )
        ))

        assertEquals(listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf(
                "–",
                "mokykis",
                "tegu mokosi / tesimoko",
                "mokykimės",
                "mokykitės",
                "tegu mokosi / tesimoko"
            ))), sut!!.getConjugationsFor(verb))
        assertEquals(1, inner.getConjugationsCount)
    }

    @Test
    fun test_error() = runTest {
        val verb = Verb("agdfsgfsdg", "fdgdfgdfsg", "fdsgffg")
        var caughtException = false

        inner.result = listOf(VerbConjugation(
            verb = verb,
            tense = Tense.Imperative,
            forms = listOf(
                "–",
                "kalbėk",
                "–",
                "kalbėkime",
                "kalbėkite",
                "–"
            )
        ))

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertEquals(1, inner.getConjugationsCount)
    }
}