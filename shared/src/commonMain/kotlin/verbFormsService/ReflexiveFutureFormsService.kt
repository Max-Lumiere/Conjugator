package verbFormsService

class ReflexiveFutureFormsService(
    private val commonFormsService: VerbFormsService
    ): VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = commonFormsService.getVerbFormsFor(form).toMutableList()

        forms[0] += "osi"
        forms[1] += "esi"
        forms[2] += "is"
        forms[3] = forms[3].dropLast(1) + "ės"
        forms[4] = forms[4].dropLast(1) + "ės"
        forms[5] += "is"

        return forms
    }
}