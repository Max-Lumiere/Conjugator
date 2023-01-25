package verbFormsServiceTests

import verbFormsService.ETypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ETypeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ETypeFormsService()
    }

    @Test
    fun test_e() {
        val form = "griaudė"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("griaudiau", "griaudei", "griaudė", "griaudėme", "griaudėte", "griaudė")
        )
    }

    @Test
    fun test_te() {
        val form = "švietė"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("šviečiau", "švietei", "švietė", "švietėme", "švietėte", "švietė")
        )
    }
}