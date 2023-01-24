package conjugationsService

import entities.Tense
import entities.Verb
import entities.VerbConjugation
import verbFormsService.VerbFormsService

class PastConjugationsService(
    private val oTypeService: VerbFormsService,
    private val eTypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val reflexiveETypeService: VerbFormsService
): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val forms: List<String>

        if (verb.past.endsWith("si")) {
            forms = when (verb.past.dropLast(2).last()) {
                'ė' -> reflexiveETypeService.getVerbFormsFor(verb.past)
                'o' -> reflexiveOTypeService.getVerbFormsFor(verb.past)
                else -> {
                    throw Exception("Wrong past form ${verb.past}")
                }
            }
        } else {
            forms = when (verb.past.last()) {
                'ė' -> eTypeService.getVerbFormsFor(verb.past)
                'o' -> oTypeService.getVerbFormsFor(verb.past)
                else -> {
                    throw Exception("Wrong past form ${verb.past}")
                }
            }
        }

        return listOf(VerbConjugation(verb, Tense.Past, forms))
    }

}