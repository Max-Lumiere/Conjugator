package conjugationsService

import entities.Tense
import entities.Verb
import entities.VerbConjugation
import verbFormsService.VerbFormsService

class SimpleConjugationsService(
    private val tense: Tense,
    private val commonFormsService: VerbFormsService,
    private val reflexiveFormsService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val form = verb.infinitive

        val forms = if (form.endsWith("tis")) {
            reflexiveFormsService.getVerbFormsFor(form)
        } else if (form.endsWith("ti")) {
            commonFormsService.getVerbFormsFor(form)
        } else {
            throw Exception("Wrong infinitive ${verb.infinitive}")
        }

        return listOf(VerbConjugation(verb, tense, forms))
    }

}