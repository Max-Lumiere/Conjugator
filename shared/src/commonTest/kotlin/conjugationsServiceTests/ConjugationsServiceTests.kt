package conjugationsServiceTests

import conjugationsService.ConjugationsService
import verbFormsService.VerbFormsService

open class ConjugationsServiceTests {

    class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return emptyList()
        }
    }

    var sut: ConjugationsService? = null
}