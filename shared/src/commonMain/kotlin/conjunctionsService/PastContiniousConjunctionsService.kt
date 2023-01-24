package conjunctionsService

import entities.Tense
import entities.Verb
import entities.VerbConjunction
import verbFormsService.VerbFormsService

class PastContiniousConjunctionsService(
    private val oTypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService
): ConjunctionsService {

    @Throws(Exception::class)
    override suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction> {
        val forms: List<String> = if (verb.infinitive.endsWith("tis")) {
            val form = verb.infinitive.dropLast(3) + "davosi"

            reflexiveOTypeService.getVerbFormsFor(form)
        } else if (verb.infinitive.endsWith("ti")) {
            val form = verb.infinitive.dropLast(2) + "davo"

            oTypeService.getVerbFormsFor(form)
        } else {
            throw Exception("Wrong infinitive form ${verb.infinitive}")
        }

        return listOf(VerbConjunction(verb, Tense.PastContiniuos, forms))
    }

}