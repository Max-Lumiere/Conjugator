package verbFormsServiceTests

import verbFormsService.ReflexiveOTypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveOTypeFormsServiceTests {

    private var sut: ReflexiveOTypeFormsService? = null

    @BeforeTest
    fun setUp() {
        sut = ReflexiveOTypeFormsService()
    }

    @Test
    fun test_i() {
        assertEquals(
            sut!!.getVerbFormsFor("rašosi"),
            listOf("rašausi", "rašaisi", "rašosi", "rašomės", "rašotės", "rašosi")
        )
    }
}