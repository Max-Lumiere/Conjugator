package verbFormsService

class FirstPresentVerbFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): Array<String> {
        val forms = Array(6) { "" }

        forms[0] = form.dropLast(1) + "u"

        if (form[form.length - 2] == 'i') {
            forms[1] = form.dropLast(1)
        } else {
            forms[1] = form.dropLast(1) + "i"
        }

        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}