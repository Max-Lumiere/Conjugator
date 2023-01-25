package verbFormsServiceTests

import verbFormsService.ReflexiveETypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveETypeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ReflexiveETypeFormsService()
    }

    @Test
    fun test_esi() {
        val form = "rašėsi"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("rašiausi", "rašeisi", "rašėsi", "rašėmės", "rašėtės", "rašėsi")
        )
    }

    @Test
    fun test_tesi() {
        val form = "švietėsi"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("šviečiausi", "švieteisi", "švietėsi", "švietėmės", "švietėtės", "švietėsi")
        )
    }
}