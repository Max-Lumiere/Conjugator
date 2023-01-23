package verbFormsService

class ReflexiveOTypeFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(3) + "ausi"
        forms[1] = form.dropLast(3) + "aisi"
        forms[2] = form
        forms[3] = form.dropLast(2) + "mės"
        forms[4] = form.dropLast(2) + "tės"
        forms[5] = form

        return forms
    }
}