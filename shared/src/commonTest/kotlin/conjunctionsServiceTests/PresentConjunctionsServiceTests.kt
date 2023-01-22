package conjunctionsServiceTests

import conjunctionsService.PresentConjunctionsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PresentConjunctionsServiceTests {
    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return List(6) { "" }
        }
    }

    private var formsMock = VerbFormsServiceMock()
    private var sut: PresentConjunctionsService? = null

    @BeforeTest
    fun setUp() {
        formsMock = VerbFormsServiceMock()
        sut = PresentConjunctionsService(formsMock)
    }

    @Test
    fun test_present_conjunctions() = runTest {
        val verb = Verb("","","")

        val result = sut!!.getConjunctionsFor(verb)

        assertTrue(formsMock.getFormsCount == 1)
        assertEquals(result[0].tense, Tense.Present)
    }
}