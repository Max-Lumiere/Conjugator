package verbFormsService

class ButiImperativeVerbFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = "–"
        forms[1] = "būk"
        forms[2] = "tegu būna"
        forms[3] = "būkime"
        forms[4] = "būkite"
        forms[5] = "tegu būna"

        return forms
    }
}