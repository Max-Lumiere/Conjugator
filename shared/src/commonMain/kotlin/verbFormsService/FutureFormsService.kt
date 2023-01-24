package verbFormsService

class FutureFormsService: VerbFormsService {

    override fun getVerbFormsFor(form: String): List<String> {
        val forms = MutableList(6) { "" }

        val base = when (form.dropLast(2).last()) {
            's' -> form.dropLast(2)
            'š' -> form.dropLast(2)
            'z' -> form.dropLast(3) + 's'
            'ž' -> form.dropLast(3) + 'š'
            else -> form.dropLast(2) + 's'
        }
        forms[0] = base + "iu"
        forms[1] = base + "i"
        forms[2] = base
        forms[3] = base + "ime"
        forms[4] = base + "ite"
        forms[5] = base

        return forms
    }
}