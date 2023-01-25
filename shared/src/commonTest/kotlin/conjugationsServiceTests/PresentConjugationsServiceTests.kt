package conjugationsServiceTests

import conjugationsService.PresentConjugationsService
import entities.Tense
import entities.Verb
import kotlinx.coroutines.test.runTest
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PresentConjugationsServiceTests: ConjugationsServiceTests() {

    private var aType = VerbFormsServiceMock()
    private var iType = VerbFormsServiceMock()
    private var oType = VerbFormsServiceMock()
    private var reflexiveAType = VerbFormsServiceMock()
    private var reflexiveIType = VerbFormsServiceMock()
    private var reflexiveOType = VerbFormsServiceMock()
    private var buti = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        aType = VerbFormsServiceMock()
        iType = VerbFormsServiceMock()
        oType = VerbFormsServiceMock()
        reflexiveAType = VerbFormsServiceMock()
        reflexiveIType = VerbFormsServiceMock()
        reflexiveOType = VerbFormsServiceMock()
        buti = VerbFormsServiceMock()
        sut = PresentConjugationsService(
            aType,
            iType,
            oType,
            reflexiveAType,
            reflexiveIType,
            reflexiveOType,
            buti
        )
    }

    @Test
    fun test_present_conjunctions() = runTest {
        val verb = Verb("","yra","")

        val result = sut!!.getConjugationsFor(verb)

        assertEquals(result[0].tense, Tense.Present)
    }

    @Test
    fun testGetConjugation_first_type() = runTest {
        val verb = Verb("", "verkia", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 1)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_second_type() = runTest {
        val verb = Verb("", "tyli", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 1)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_third_type() = runTest {
        val verb = Verb("", "moko", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 1)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_buti() = runTest {
        val verb = Verb("", "yra", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 1)
    }

    @Test
    fun testGetConjugation_reflexive_a() = runTest {
        val verb = Verb("", "nešasi", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 1)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_i() = runTest {
        val verb = Verb("", "tikisi", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 1)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_reflexive_o() = runTest {
        val verb = Verb("", "rašosi", "")

        sut!!.getConjugationsFor(verb)

        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 1)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_si_error() = runTest {
        val verb = Verb("", "mokzxsi", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }

    @Test
    fun testGetConjugation_error() = runTest {
        val verb = Verb("", "mokzx", "")
        var caughtException = false

        try {
            sut!!.getConjugationsFor(verb)
        } catch(e: Exception) {
            caughtException = true
        }

        assertTrue(caughtException)
        assertTrue(aType.getFormsCount == 0)
        assertTrue(iType.getFormsCount == 0)
        assertTrue(oType.getFormsCount == 0)
        assertTrue(reflexiveAType.getFormsCount == 0)
        assertTrue(reflexiveIType.getFormsCount == 0)
        assertTrue(reflexiveOType.getFormsCount == 0)
        assertTrue(buti.getFormsCount == 0)
    }
}