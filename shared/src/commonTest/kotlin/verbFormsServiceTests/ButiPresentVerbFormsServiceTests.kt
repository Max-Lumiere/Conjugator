package verbFormsServiceTests

import verbFormsService.ButiPresentVerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ButiPresentVerbFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ButiPresentVerbFormsService()
    }

    @Test
    fun test_buti() {
        val forms = sut!!.getVerbFormsFor("yra")

        assertContentEquals(forms, listOf("esu", "esi", "yra", "esame", "esate", "yra"))
    }
}