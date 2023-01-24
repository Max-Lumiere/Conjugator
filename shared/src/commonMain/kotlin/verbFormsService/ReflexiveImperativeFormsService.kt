package verbFormsService

class ReflexiveImperativeFormsService(
    private val commonFormsService: VerbFormsService
    ): VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = commonFormsService.getVerbFormsFor(form.dropLast(1)).toMutableList()

        forms[1] += "is"
        forms[3] = forms[3].dropLast(1) + "ės"
        forms[4] = forms[4].dropLast(1) + "ės"

        return forms
    }
}