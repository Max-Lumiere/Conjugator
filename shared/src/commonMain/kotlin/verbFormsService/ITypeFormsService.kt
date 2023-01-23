package verbFormsService

class ITypeFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form + "u"
        forms[1] = form
        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}