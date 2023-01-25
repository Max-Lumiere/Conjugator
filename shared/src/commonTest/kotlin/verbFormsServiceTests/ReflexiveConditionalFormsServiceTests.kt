package verbFormsServiceTests

import verbFormsService.ReflexiveConditionalFormsService
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveConditionalFormsServiceTests: VerbFormsServiceTests() {
    private var verbFormsMock: VerbFormsServiceMock = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        verbFormsMock = VerbFormsServiceMock()
        sut = ReflexiveConditionalFormsService(verbFormsMock)
        verbFormsMock.result = listOf(
            "mokyčiau",
            "mokytum",
            "mokytų",
            "mokytume",
            "mokytume",
            "mokytų"
        )
    }

    @Test
    fun test_conjugation() {
        assertEquals(listOf(
            "mokyčiausi",
            "mokytumeisi",
            "mokytųsi",
            "mokytumėmės",
            "mokytumėtės",
            "mokytųsi"
        ), sut!!.getVerbFormsFor("mokytis"))
        assertEquals(1, verbFormsMock.getFormsCount)
    }

    @Test
    fun test_passed_parameter() {
        sut!!.getVerbFormsFor("mokytis")
        assertEquals("mokyti", verbFormsMock.lastFormPassed)
        assertEquals(1, verbFormsMock.getFormsCount)
    }
}