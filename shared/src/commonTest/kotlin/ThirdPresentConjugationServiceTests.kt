import conjugationService.ThirdPresentConjugationService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class ThirdPresentConjugationServiceTests {

    private var sut: ThirdPresentConjugationService? = null

    private fun prepare() {
        sut = ThirdPresentConjugationService()
    }

    @Test
    fun testGetConjugation_o() {
        prepare()

        val verb = Verb("mokyti", "moko", "mokė")
        val result = sut!!.getConjunctionFor(verb)

        assertEquals(result.forms[0], "mokau")
        assertEquals(result.forms[1], "mokai")
        assertEquals(result.forms[2], "moko")
        assertEquals(result.forms[3], "mokome")
        assertEquals(result.forms[4], "mokote")
        assertEquals(result.forms[5], "moko")
    }
}