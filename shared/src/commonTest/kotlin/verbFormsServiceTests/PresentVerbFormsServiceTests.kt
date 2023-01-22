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
    private var buti = VerbFormsServiceMock()
    private var sut = PresentVerbFormsService(first, second, third, buti)

    @BeforeTest
    fun setUp() {
        first = VerbFormsServiceMock()
        second = VerbFormsServiceMock()
        third = VerbFormsServiceMock()
        buti = VerbFormsServiceMock()
        sut = PresentVerbFormsService(first, second, third, buti)
    }

    @Test
    fun testGetConjugation_first_type() {
        sut.getVerbFormsFor("verkia")

        assertTrue(first.getConjugationCount == 1)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_second_type() {
        sut.getVerbFormsFor("tyli")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 1)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_third_type() {
        sut.getVerbFormsFor("moko")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 1)
        assertTrue(buti.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_buti() {
        sut.getVerbFormsFor("yra")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 1)
    }

    @Test
    fun testGetConjugation_error() {
        var caughtException = false

        try {
            sut.getVerbFormsFor("mokzx")
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
        assertTrue(buti.getConjugationCount == 0)
    }
}