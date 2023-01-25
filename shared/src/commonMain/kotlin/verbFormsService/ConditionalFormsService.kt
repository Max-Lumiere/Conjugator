package verbFormsService

class ConditionalFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = form.dropLast(2) + "čiau"
        forms[1] = form.dropLast(2) + "tum"
        forms[2] = form.dropLast(2) + "tų"
        forms[3] = form.dropLast(2) + "tume"
        forms[4] = form.dropLast(2) + "tute"
        forms[5] = form.dropLast(2) + "tų"

        return forms
    }
}