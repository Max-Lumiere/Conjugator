package verbFormsService

class PresentVerbFormsService(
    private val firstTypeService: VerbFormsService,
    private val secondTypeService: VerbFormsService,
    private val thirdTypeService: VerbFormsService,
    private val butiService: VerbFormsService
) : VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {

        if (form == "yra") {
            return butiService.getVerbFormsFor(form)
        }

        return when (form.last()) {
            'a' -> firstTypeService.getVerbFormsFor(form)
            'i' -> secondTypeService.getVerbFormsFor(form)
            'o' -> thirdTypeService.getVerbFormsFor(form)
            else -> {
                throw Exception("Wrong present form ${form}")
            }
        }
    }
}