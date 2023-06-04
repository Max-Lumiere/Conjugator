package verbFormsServiceTests

import verbFormsService.ButiImperativeVerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ButiImperativeVerbFormsService: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ButiImperativeVerbFormsService()
    }

    @Test
    fun test_buti() {
        assertContentEquals(sut!!.getVerbFormsFor(""), listOf(
            "–",
            "būk",
            "tegu būna",
            "būkime",
            "būkite",
            "tegu būna"
        ))
    }
}