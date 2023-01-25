package verbFormsServiceTests

import verbFormsService.ATypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class ATypeFormsServiceTests: VerbFormsServiceTests() {

    private fun prepare() {
        sut = ATypeFormsService()
    }

    @Test
    fun testGetConjugation_a() {
        prepare()

        val verb = Verb("duoti", "duoda", "davė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "duodu")
        assertEquals(forms[1], "duodi")
        assertEquals(forms[2], "duoda")
        assertEquals(forms[3], "duodame")
        assertEquals(forms[4], "duodate")
        assertEquals(forms[5], "duoda")
    }

    @Test
    fun testGetConjugation_ia() {
        prepare()

        val verb = Verb("veikti", "veikia", "veikė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "veikiu")
        assertEquals(forms[1], "veiki")
        assertEquals(forms[2], "veikia")
        assertEquals(forms[3], "veikiame")
        assertEquals(forms[4], "veikiate")
        assertEquals(forms[5], "veikia")
    }
}