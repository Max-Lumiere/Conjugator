package conjugationsService

import entities.Tense
import entities.Verb
import entities.VerbConjugation
import verbFormsService.VerbFormsService

class FutureConjugationsService(
    private val commonFormsService: VerbFormsService,
    private val reflexiveFormsService: VerbFormsService,
    private val butiFormsService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val forms = if (verb.infinitive == "būti") {
            butiFormsService.getVerbFormsFor(verb.infinitive)
        } else if (verb.infinitive.endsWith("tis")) {
            reflexiveFormsService.getVerbFormsFor(verb.infinitive)
        } else if (verb.infinitive.endsWith("ti")) {
            commonFormsService.getVerbFormsFor(verb.infinitive)
        } else {
            throw Exception("Wrong infinitive ${verb.infinitive}")
        }

        return listOf(VerbConjugation(verb, Tense.Future, forms))
    }
}