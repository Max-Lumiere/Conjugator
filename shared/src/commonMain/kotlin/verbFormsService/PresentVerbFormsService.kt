package verbFormsService

class PresentVerbFormsService(
    private val firstTypeService: VerbFormsService,
    private val secondTypeService: VerbFormsService,
    private val thirdTypeService: VerbFormsService,
    private val reflexiveATypeService: VerbFormsService,
    private val reflexiveITypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val butiService: VerbFormsService
    ) : VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {

        if (form == "yra") {
            return butiService.getVerbFormsFor(form)
        }

        if (form.endsWith("si")) {
            return when (form.dropLast(2).last()) {
                'a' -> reflexiveATypeService.getVerbFormsFor(form)
                'i' -> reflexiveITypeService.getVerbFormsFor(form)
                'o' -> reflexiveOTypeService.getVerbFormsFor(form)
                else -> {
                    throw Exception("Wrong present form ${form}")
                }
            }
        } else {
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
}