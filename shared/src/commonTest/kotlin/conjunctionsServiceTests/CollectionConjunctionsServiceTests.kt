package conjunctionsServiceTests

import conjunctionsService.CollectionConjunctionsService
import conjunctionsService.ConjunctionsService
import entities.Tense
import entities.Verb
import entities.VerbConjunction
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class CollectionConjunctionsServiceTests {
    private var sut: CollectionConjunctionsService? = null
    private var mock1 = ConjunctionsServiceMock()
    private var mock2 = ConjunctionsServiceMock()
    private var mock3 = ConjunctionsServiceMock()

    private class ConjunctionsServiceMock: ConjunctionsService {
        var result: Array<VerbConjunction> = emptyArray()

        @Throws(Exception::class)
        override suspend fun getConjunctionsFor(verb: Verb): Array<VerbConjunction> {
            return result
        }

    }

    @BeforeTest
    fun setUp() {
        sut = CollectionConjunctionsService(arrayOf(mock1, mock2, mock3))
    }

    @Test
    fun testService_success() = runTest {
        val verb = Verb("","","")
        val result1 = arrayOf(VerbConjunction(verb, Tense.Present, emptyArray()))
        val result2 = arrayOf(
            VerbConjunction(verb, Tense.Past, emptyArray()),
            VerbConjunction(verb, Tense.PastContiniuos, emptyArray())
        )
        val result3 = arrayOf(VerbConjunction(verb, Tense.Future, emptyArray()))

        mock1.result = result1
        mock2.result = result2
        mock3.result = result3

        val result = sut!!.getConjunctionsFor(verb)

        assertContentEquals(result1 + result2 + result3, result)
    }

}