package conjugationsServiceTests

import conjugationsService.FutureConjugationsService
import conjugationsService.PastContiniousConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FutureConjugationsServiceTests {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return emptyList()
        }
    }

    private var commonType = VerbFormsServiceMock()
    private var reflexiveType = VerbFormsServiceMock()
    private var buti = VerbFormsServiceMock()
    private var sut: FutureConjugationsService? = null

    @BeforeTest
    fun setUp() {
        commonType = VerbFormsServiceMock()
        reflexiveType = VerbFormsServiceMock()
        buti = VerbFormsServiceMock()
        sut = FutureConjugationsService(commonType, reflexiveType, buti)
    }

    @Test
    fun test_future_conjugations() = runTest {
        val verb = Verb("buti","","")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.Future)
    }

    @Test
    fun test_buti_conjugation() = runTest {
        val verb = Verb("būti", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(buti.getFormsCount == 1)
        assertTrue(reflexiveType.getFormsCount == 0)
        assertTrue(commonType.getFormsCount == 0)
    }

    @Test
    fun test_reflexive_conjugation() = runTest {
        val verb = Verb("darytis", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(buti.getFormsCount == 0)
        assertTrue(reflexiveType.getFormsCount == 1)
        assertTrue(commonType.getFormsCount == 0)
    }

    @Test
    fun test_common_conjugation() = runTest {
        val verb = Verb("daryti", "", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(buti.getFormsCount == 0)
        assertTrue(reflexiveType.getFormsCount == 0)
        assertTrue(commonType.getFormsCount == 1)
    }

    @Test
    fun test_conjugation_error() = runTest {
        val verb = Verb("mokzx", "", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(commonType.getFormsCount == 0)
        assertTrue(reflexiveType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }
}