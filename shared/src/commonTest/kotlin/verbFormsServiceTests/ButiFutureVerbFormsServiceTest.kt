package verbFormsServiceTests

import verbFormsService.ButiFutureVerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ButiFutureVerbFormsServiceTest: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ButiFutureVerbFormsService()
    }

    @Test
    fun test_buti() {
        assertContentEquals(sut!!.getVerbFormsFor(""), listOf(
            "būsiu",
            "būsi",
            "bus",
            "būsime",
            "būsite",
            "bus"
        ))
    }
}