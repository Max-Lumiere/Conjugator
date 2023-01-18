package verbFormsServiceTests

import verbFormsService.VerbFormsService
import verbFormsService.PresentVerbFormsService
import kotlin.test.Test
import kotlin.test.assertTrue

class PresentVerbFormsServiceTests {
    private class VerbFormsServiceMock: VerbFormsService {
        var getConjugationCount = 0
        override fun getVerbFormsFor(form: String): Array<String> {
            getConjugationCount += 1
            return Array(6) { "" }
        }
    }

    private var first = VerbFormsServiceMock()
    private var second = VerbFormsServiceMock()
    private var third = VerbFormsServiceMock()
    private var sut = PresentVerbFormsService(first, second, third)

    private fun prepare() {
        first = VerbFormsServiceMock()
        second = VerbFormsServiceMock()
        third = VerbFormsServiceMock()
        sut = PresentVerbFormsService(first, second, third)
    }

    @Test
    fun testGetConjugation_first_type() {
        prepare()

        sut.getVerbFormsFor("verkia")

        assertTrue(first.getConjugationCount == 1)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_second_type() {
        prepare()

        sut.getVerbFormsFor("tyli")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 1)
        assertTrue(third.getConjugationCount == 0)
    }

    @Test
    fun testGetConjugation_third_type() {
        prepare()

        sut.getVerbFormsFor("moko")

        assertTrue(first.getConjugationCount == 0)
        assertTrue(second.getConjugationCount == 0)
        assertTrue(third.getConjugationCount == 1)
    }

    @Test
    fun testGetConjugation_error() {
        prepare()
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
    }
}