package verbFormsService

class ButiPresentVerbFormsService: VerbFormsService {

    @Throws(Exception::class)
    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        forms[0] = "esu"
        forms[1] = "esi"
        forms[2] = "yra"
        forms[3] = "esame"
        forms[4] = "esate"
        forms[5] = "yra"

        return forms
    }
}