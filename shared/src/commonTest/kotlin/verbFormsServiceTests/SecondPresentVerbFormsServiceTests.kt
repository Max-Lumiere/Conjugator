package verbFormsServiceTests

import verbFormsService.SecondPresentVerbFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class SecondPresentVerbFormsServiceTests {
    private var sut: SecondPresentVerbFormsService? = null

    private fun prepare() {
        sut = SecondPresentVerbFormsService()
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