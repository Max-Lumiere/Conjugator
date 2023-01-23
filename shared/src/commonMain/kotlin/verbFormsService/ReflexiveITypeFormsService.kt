package verbFormsService

class ReflexiveITypeFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(2) + "uosi"
        forms[1] = form.dropLast(2) + "esi"
        forms[2] = form
        forms[3] = form.dropLast(2) + "mės"
        forms[4] = form.dropLast(2) + "tės"
        forms[5] = form

        return forms
    }
}