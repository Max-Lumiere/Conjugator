package verbFormsServiceTests

import verbFormsService.FutureFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FutureFormsServiceTests {

    private var sut: FutureFormsService? = null

    @BeforeTest
    fun setUp() {
        sut = FutureFormsService()
    }

    @Test
    fun test_common_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("tylėti"), listOf(
            "tylėsiu",
            "tylėsi",
            "tylės",
            "tylėsime",
            "tylėsite",
            "tylės"
        ))
    }

    @Test
    fun test_double_s_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("skristi"), listOf(
            "skrisiu",
            "skrisi",
            "skris",
            "skrisime",
            "skrisite",
            "skris"
        ))
    }

    @Test
    fun test_sh_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("nešti"), listOf(
            "nešiu",
            "neši",
            "neš",
            "nešime",
            "nešite",
            "neš"
        ))
    }

    @Test
    fun test_j_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("vežti"), listOf(
            "vešiu",
            "veši",
            "veš",
            "vešime",
            "vešite",
            "veš"
        ))
    }

    @Test
    fun test_z_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("megzti"), listOf(
            "megsiu",
            "megsi",
            "megs",
            "megsime",
            "megsite",
            "megs"
        ))
    }
}