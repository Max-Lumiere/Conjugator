package verbFormsServiceTests

import verbFormsService.ReflexiveFutureFormsService
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ReflexiveFutureFormsServiceTests {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        var result: List<String> = emptyList()

        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return result
        }
    }

    private var commonService = VerbFormsServiceMock()
    private var sut: ReflexiveFutureFormsService? = null

    @BeforeTest
    fun setUp() {
        commonService = VerbFormsServiceMock()
        sut = ReflexiveFutureFormsService(commonService)
    }

    @Test
    fun test_conjugation() {
        commonService.result = listOf(
            "prausiu",
            "prausi",
            "praus",
            "prausime",
            "prausite",
            "praus"
        )

        assertContentEquals(sut!!.getVerbFormsFor(""), listOf(
            "prausiuosi",
            "prausiesi",
            "prausis",
            "prausimės",
            "prausitės",
            "prausis"
        ))
        assertEquals(1, commonService.getFormsCount)
    }

}