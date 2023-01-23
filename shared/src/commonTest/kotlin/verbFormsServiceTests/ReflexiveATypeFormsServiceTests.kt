package verbFormsServiceTests

import verbFormsService.ReflexiveATypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveATypeFormsServiceTests {
    private var sut: ReflexiveATypeFormsService? = null

    @BeforeTest
    fun setUp() {
        sut = ReflexiveATypeFormsService()
    }

    @Test
    fun test_a() {
        assertEquals(
            sut!!.getVerbFormsFor("nešasi"),
            listOf("nešuosi", "nešiesi", "nešasi", "nešamės", "nešatės", "nešasi")
        )
    }

    @Test
    fun test_ia() {
        assertEquals(
            sut!!.getVerbFormsFor("veikiasi"),
            listOf("veikiuosi", "veikiesi", "veikiasi", "veikiamės", "veikiatės", "veikiasi")
        )
    }
}