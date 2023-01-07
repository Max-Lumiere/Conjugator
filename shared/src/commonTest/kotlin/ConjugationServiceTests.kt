import conjugationService.ConjugationService
import conjugationService.PresentConjugationService
import entities.Verb
import entities.VerbConjunction
import kotlin.test.Test
import kotlin.test.assertTrue

class ConjugationServiceTests {
    private class ConjugationServiceMock: ConjugationService {
        var getConjugationCount = 0
        override fun getConjunctionFor(verb: Verb): VerbConjunction {
            getConjugationCount += 1
            return VerbConjunction(Verb("","",""), Array(6) { "" })
        }
    }

    private var first = ConjugationServiceMock()
    private var second = ConjugationServiceMock()
    private var third = ConjugationServiceMock()
    private var sut = PresentConjugationService(first, second, third)

    private fun prepare() {
        first = ConjugationServiceMock()
        second = ConjugationServiceMock()
        third = ConjugationServiceMock()
        sut = PresentConjugationService(first, second, third)
    }

    @Test
    fun testGetConjugation_first_type() {
        prepare()

        sut.getConjunctionFor(Verb("verkti","verkia","verkė"))

        assertTrue(first.getConjugationCount == 1)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_second_type() {
        prepare()

        sut.getConjunctionFor(Verb("tylėti","tyli","tylėjo"))

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 1)
        assertTrue(third.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_third_type() {
        prepare()

        sut.getConjunctionFor(Verb("mokyti","moko","mokė"))

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 1)
    }

    @Test
    fun testGetConjugation_error() {
        prepare()
        var caughtException = false

        try {
            sut.getConjunctionFor(Verb("addasdda","mokzx","dsfsdf"))
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
    }
}