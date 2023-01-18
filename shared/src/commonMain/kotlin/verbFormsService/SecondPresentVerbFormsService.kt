package verbFormsService

class SecondPresentVerbFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): Array<String> {
        val forms = Array(6) { "" }

        forms[0] = form + "u"
        forms[1] = form
        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}