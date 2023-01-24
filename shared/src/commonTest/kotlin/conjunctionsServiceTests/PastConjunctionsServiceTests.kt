package conjunctionsServiceTests

import conjunctionsService.PastConjunctionsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PastConjunctionsServiceTests {

    private class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return emptyList()
        }
    }

    private var eType = VerbFormsServiceMock()
    private var oType = VerbFormsServiceMock()
    private var reflexiveEType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()
    private var sut: PastConjunctionsService? = null

    @BeforeTest
    fun setUp() {
        eType = VerbFormsServiceMock()
        oType = VerbFormsServiceMock()
        reflexiveEType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        sut = PastConjunctionsService(
            oType,
            eType,
            reflexiveOType,
            reflexiveEType
        )
    }

    @Test
    fun test_past_conjunctions() = runTest {
        val verb = Verb("","","buvo")

        val result = sut!!.getConjunctionsFor(verb)

        assertEquals(result[0].tense, Tense.Past)
    }

    @Test
    fun testGetConjugation_o_type() = runTest {
        val verb = Verb("", "", "buvo")

        sut!!.getConjunctionsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 1)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_e_type() = runTest {
        val verb = Verb("", "", "darė")

        sut!!.getConjunctionsFor(verb)

        assertTrue(eType.getFormsCount == 1)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_e() = runTest {
        val verb = Verb("", "", "darėsi")

        sut!!.getConjunctionsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 1)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() = runTest {
        val verb = Verb("", "", "dainavosi")

        sut!!.getConjunctionsFor(verb)

        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 1)
    }

    @Test
    fun testGetConjugation_si_error() = runTest {
        val verb = Verb("", "", "mokzxsi")
        var caughtException = false

        try {
            sut!!.getConjunctionsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_error() = runTest {
        val verb = Verb("", "", "mokzx")
        var caughtException = false

        try {
            sut!!.getConjunctionsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(eType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveEType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
    }
    
}