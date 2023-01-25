package verbFormsServiceTests

import verbFormsService.ReflexiveImperativeFormsService
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveImperativeFormsServiceTests: VerbFormsServiceTests() {
    private var commonService = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        commonService = VerbFormsServiceMock()
        sut = ReflexiveImperativeFormsService(commonService)
        commonService.result = listOf(
            "–",
            "tvarkyk",
            "–",
            "tvarkykime",
            "tvarkykite",
            "–"
        )
    }

    @Test
    fun test_common_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("tvarkytis"), listOf(
            "–",
            "tvarkykis",
            "–",
            "tvarkykimės",
            "tvarkykitės",
            "–"
        ))
        assertEquals(1, commonService.getFormsCount)
    }

    @Test
    fun test_passed_parameter() {
        sut!!.getVerbFormsFor("tvarkytis")
        assertEquals("tvarkyti", commonService.lastFormPassed)
        assertEquals(1, commonService.getFormsCount)
    }
}