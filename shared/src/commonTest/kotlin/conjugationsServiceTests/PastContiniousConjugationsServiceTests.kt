package conjugationsServiceTests

import conjugationsService.PastContiniousConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PastContiniousConjugationsServiceTests {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return emptyList()
        }
    }

    private var oType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()
    private var sut: PastContiniousConjugationsService? = null

    @BeforeTest
    fun setUp() {
        oType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        sut = PastContiniousConjugationsService(oType, reflexiveOType)
    }

    @Test
    fun test_past_continious_conjunctions() = runTest {
        val verb = Verb("buti","","")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.PastContiniuos)
    }

    @Test
    fun testGetConjugation() = runTest {
        val verb = Verb("būti", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(oType.getFormsCount == 1)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() = runTest {
        val verb = Verb("darytis", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 1)
    }

    @Test
    fun testGetConjugation_error() = runTest {
        val verb = Verb("mokzx", "", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }
}