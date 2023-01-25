package conjugationsServiceTests

import conjugationsService.CollectionConjugationsService
import conjugationsService.ConjugationsService
import entities.Tense
import entities.Verb
import entities.VerbConjugation
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class CollectionConjugationsServiceTests: ConjugationsServiceTests() {

    private var mock1 = ConjugationsServiceMock()
    private var mock2 = ConjugationsServiceMock()
    private var mock3 = ConjugationsServiceMock()

    private class ConjugationsServiceMock: ConjugationsService {
        var result: List<VerbConjugation> = emptyList()

        @Throws(Exception::class)
        override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
            return result
        }

    }

    @BeforeTest
    fun setUp() {
        sut = CollectionConjugationsService(listOf(mock1, mock2, mock3))
    }

    @Test
    fun testService_success() = runTest {
        val verb = Verb("","","")
        val result1 = listOf(VerbConjugation(verb, Tense.Present, emptyList()))
        val result2 = listOf(
            VerbConjugation(verb, Tense.Past, emptyList()),
            VerbConjugation(verb, Tense.PastContiniuos, emptyList())
        )
        val result3 = listOf(VerbConjugation(verb, Tense.Future, emptyList()))

        mock1.result = result1
        mock2.result = result2
        mock3.result = result3

        val result = sut!!.getConjugationsFor(verb)

        assertContentEquals(result1 + result2 + result3, result)
    }

}