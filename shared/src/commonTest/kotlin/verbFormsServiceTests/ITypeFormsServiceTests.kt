package verbFormsServiceTests

import verbFormsService.ITypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class ITypeFormsServiceTests {
    private var sut: ITypeFormsService? = null

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