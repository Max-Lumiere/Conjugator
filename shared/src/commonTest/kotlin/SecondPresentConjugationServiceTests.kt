import conjugationService.SecondPresentConjugationService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class SecondPresentConjugationServiceTests {
    private var sut: SecondPresentConjugationService? = null

    private fun prepare() {
        sut = SecondPresentConjugationService()
    }

    @Test
    fun testGetConjugation_i() {
        prepare()

        val verb = Verb("tylėti", "tyli", "tyjėjo")
        val result = sut!!.getConjunctionFor(verb)

        assertEquals(result.forms[0], "tyliu")
        assertEquals(result.forms[1], "tyli")
        assertEquals(result.forms[2], "tyli")
        assertEquals(result.forms[3], "tylime")
        assertEquals(result.forms[4], "tylite")
        assertEquals(result.forms[5], "tyli")
    }
}