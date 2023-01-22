package verbFormsService

class ThirdPresentVerbFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(1) + "au"
        forms[1] = form.dropLast(1) + "ai"
        forms[2] = form
        forms[3] = form + "me"
        forms[4] = form + "te"
        forms[5] = form

        return forms
    }
}