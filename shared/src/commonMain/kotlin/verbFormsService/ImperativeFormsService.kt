package verbFormsService

class ImperativeFormsService: VerbFormsService {
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "–" }
        val base = form.dropLast(2) + "k"

        forms[1] = base
        forms[3] = base + "ime"
        forms[4] = base + "ite"

        return forms
    }

}