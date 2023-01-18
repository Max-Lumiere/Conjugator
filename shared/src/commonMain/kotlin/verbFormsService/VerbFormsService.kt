package verbFormsService

interface VerbFormsService {

    @Throws(Exception::class)
    fun getVerbFormsFor(form: String): Array<String>
}