package verbFormsServiceTests

import verbFormsService.ConditionalFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConditionalFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ConditionalFormsService()
    }

    @Test
    fun test_conjugation() {
        assertEquals(listOf(
            "kalbėčiau",
            "kalbėtum",
            "kalbėtų",
            "kalbėtume",
            "kalbėtute",
            "kalbėtų"
        ), sut!!.getVerbFormsFor("kalbėti"))
    }
}