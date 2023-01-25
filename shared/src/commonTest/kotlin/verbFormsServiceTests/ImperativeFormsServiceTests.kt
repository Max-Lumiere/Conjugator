package verbFormsServiceTests

import verbFormsService.ImperativeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ImperativeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ImperativeFormsService()
    }

    @Test
    fun test_common_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("tylėti"), listOf(
            "–",
            "tylėk",
            "–",
            "tylėkime",
            "tylėkite",
            "–"
        ))
    }
}