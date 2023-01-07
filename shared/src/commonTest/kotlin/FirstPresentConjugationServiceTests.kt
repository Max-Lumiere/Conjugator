import conjugationService.FirstPresentConjugationService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class FirstPresentConjugationServiceTests {
    private var sut: FirstPresentConjugationService? = null

    private fun prepare() {
        sut = FirstPresentConjugationService()
    }

    @Test
    fun testGetConjugation_a() {
        prepare()

        val verb = Verb("duoti", "duoda", "davė")
        val result = sut!!.getConjunctionFor(verb)

        assertEquals(result.forms[0], "duodu")
        assertEquals(result.forms[1], "duodi")
        assertEquals(result.forms[2], "duoda")
        assertEquals(result.forms[3], "duodame")
        assertEquals(result.forms[4], "duodate")
        assertEquals(result.forms[5], "duoda")
    }

    @Test
    fun testGetConjugation_ia() {
        prepare()

        val verb = Verb("veikti", "veikia", "veikė")
        val result = sut!!.getConjunctionFor(verb)

        assertEquals(result.forms[0], "veikiu")
        assertEquals(result.forms[1], "veiki")
        assertEquals(result.forms[2], "veikia")
        assertEquals(result.forms[3], "veikiame")
        assertEquals(result.forms[4], "veikiate")
        assertEquals(result.forms[5], "veikia")
    }
}