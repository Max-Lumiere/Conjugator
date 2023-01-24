package conjugationsService

import entities.Tense
import entities.Verb
import entities.VerbConjugation
import verbFormsService.VerbFormsService

class PastContiniousConjugationsService(
    private val oTypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val forms: List<String> = if (verb.infinitive.endsWith("tis")) {
            val form = verb.infinitive.dropLast(3) + "davosi"

            reflexiveOTypeService.getVerbFormsFor(form)
        } else if (verb.infinitive.endsWith("ti")) {
            val form = verb.infinitive.dropLast(2) + "davo"

            oTypeService.getVerbFormsFor(form)
        } else {
            throw Exception("Wrong infinitive ${verb.infinitive}")
        }

        return listOf(VerbConjugation(verb, Tense.PastContiniuos, forms))
    }

}