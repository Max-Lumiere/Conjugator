package verbFormsService

class ETypeFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        if (form.dropLast(1).last() == 't') {
            forms[0] = form.dropLast(2) + "čiau"
        } else {
            forms[0] = form.dropLast(1) + "iau"
        }
        forms[1] = form.dropLast(1) + "ei"
        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}