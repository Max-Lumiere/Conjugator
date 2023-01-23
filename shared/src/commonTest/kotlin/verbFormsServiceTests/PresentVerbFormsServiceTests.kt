package verbFormsServiceTests

import verbFormsService.VerbFormsService
import verbFormsService.PresentVerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PresentVerbFormsServiceTests {
    private class VerbFormsServiceMock: VerbFormsService {
        var getConjugationCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getConjugationCount += 1
            return List(6) { "" }
        }
    }

    private var first = VerbFormsServiceMock()
    private var second = VerbFormsServiceMock()
    private var third = VerbFormsServiceMock()
    private var reflexiveAType = VerbFormsServiceMock()
    private var reflexiveIType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()
    private var buti = VerbFormsServiceMock()
    private var sut: PresentVerbFormsService? = null

    @BeforeTest
    fun setUp() {
        first = VerbFormsServiceMock()
        second = VerbFormsServiceMock()
        third = VerbFormsServiceMock()
        reflexiveAType = VerbFormsServiceMock()
        reflexiveIType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        buti = VerbFormsServiceMock()
        sut = PresentVerbFormsService(first,
            second,
            third,
            reflexiveAType,
            reflexiveIType,
            reflexiveOType,
            buti
        )
    }

    @Test
    fun testGetConjugation_first_type() {
        sut!!.getVerbFormsFor("verkia")

        assertTrue(first.getConjugationCount == 1)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_second_type() {
        sut!!.getVerbFormsFor("tyli")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 1)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_third_type() {
        sut!!.getVerbFormsFor("moko")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 1)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_buti() {
        sut!!.getVerbFormsFor("yra")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 1)
    }

    @Test
    fun testGetConjugation_reflexive_a() {
        sut!!.getVerbFormsFor("nešasi")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 1)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_i() {
        sut!!.getVerbFormsFor("tikisi")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 1)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() {
        sut!!.getVerbFormsFor("rašosi")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 1)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_error() {
        var caughtException = false

        try {
            sut!!.getVerbFormsFor("mokzx")
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(reflexiveAType.getConjugationCount == 0)
        assertTrue(reflexiveIType.getConjugationCount == 0)
        assertTrue(reflexiveOType.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }
}