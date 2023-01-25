package verbFormsService

class ReflexiveConditionalFormsService(
    private val commonFormsService: VerbFormsService
): VerbFormsService {
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = commonFormsService.getVerbFormsFor(form).toMutableList()

        forms[0] += "si"
        forms[1] += "eisi"
        forms[2] += "si"
        forms[3] = forms[3].dropLast(1) + "ėmės"
        forms[4] = forms[4].dropLast(1) + "ėtės"
        forms[5] += "si"

        return forms
    }
}