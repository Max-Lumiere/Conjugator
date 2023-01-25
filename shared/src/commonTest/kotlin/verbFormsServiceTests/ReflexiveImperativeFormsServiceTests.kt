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
    }

    @Test
    fun test_common_conjugation() {
        commonService.result = listOf(
            "–",
            "tvarkyk",
            "–",
            "tvarkykime",
            "tvarkykite",
            "–"
        )
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
}