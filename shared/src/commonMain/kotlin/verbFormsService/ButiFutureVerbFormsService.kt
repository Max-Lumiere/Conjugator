package verbFormsService

class ButiFutureVerbFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = "būsiu"
        forms[1] = "būsi"
        forms[2] = "bus"
        forms[3] = "būsime"
        forms[4] = "būsite"
        forms[5] = "bus"

        return forms
    }
}