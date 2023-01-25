package verbFormsServiceTests

import verbFormsService.OTypeFormsService
import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals

class OTypeFormsServiceTests: VerbFormsServiceTests() {

    private fun prepare() {
        sut = OTypeFormsService()
    }

    @Test
    fun testGetConjugation_o() {
        prepare()

        val verb = Verb("mokyti", "moko", "mokė")
        val forms = sut!!.getVerbFormsFor(verb.present)

        assertEquals(forms[0], "mokau")
        assertEquals(forms[1], "mokai")
        assertEquals(forms[2], "moko")
        assertEquals(forms[3], "mokome")
        assertEquals(forms[4], "mokote")
        assertEquals(forms[5], "moko")
    }
}