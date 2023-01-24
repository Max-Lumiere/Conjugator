package verbFormsService

class ReflexiveETypeFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        if (form.dropLast(3).last() == 't') {
            forms[0] = form.dropLast(4) + "čiausi"
        } else {
            forms[0] = form.dropLast(3) + "iausi"
        }
        forms[1] = form.dropLast(3) + "eisi"
        forms[2] = form
        forms[3] = form.dropLast(2) + "mės"
        forms[4] = form.dropLast(2) + "tės"
        forms[5] = form

        return forms
    }
}