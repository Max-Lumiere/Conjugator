package verbFormsServiceTests

import verbFormsService.ReflexiveITypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveITypeFormsServiceTests {
    private var sut: ReflexiveITypeFormsService? = null

    @BeforeTest
    fun setUp() {
        sut = ReflexiveITypeFormsService()
    }

    @Test
    fun test_i() {
        assertEquals(
            sut!!.getVerbFormsFor("tikisi"),
            listOf("tikiuosi", "tikiesi", "tikisi", "tikimės", "tikitės", "tikisi")
        )
    }

}