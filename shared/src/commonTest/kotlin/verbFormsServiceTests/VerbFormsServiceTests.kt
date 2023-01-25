package verbFormsServiceTests

import verbFormsService.VerbFormsService

open class VerbFormsServiceTests {

    class VerbFormsServiceMock: VerbFormsService {
        var getFormsCount = 0
        var result: List<String> = emptyList()

        override fun getVerbFormsFor(form: String): List<String> {
            getFormsCount += 1
            return result
        }
    }

    var sut: VerbFormsService? = null
}