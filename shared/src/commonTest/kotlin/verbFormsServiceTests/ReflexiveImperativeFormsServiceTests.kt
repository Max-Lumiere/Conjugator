package verbFormsServiceTests

import verbFormsService.ReflexiveImperativeFormsService
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveImperativeFormsServiceTests {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        var result: List<String> = emptyList()

        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return result
        }
    }

    private var commonService = VerbFormsServiceMock()
    private var sut: ReflexiveImperativeFormsService? = null

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