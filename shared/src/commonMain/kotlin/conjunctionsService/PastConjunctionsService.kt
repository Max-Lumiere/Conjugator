package conjunctionsService

import entities.Tense
import entities.Verb
import entities.VerbConjunction
import verbFormsService.VerbFormsService

class PastConjunctionsService(
    private val oTypeService: VerbFormsService,
    private val eTypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val reflexiveETypeService: VerbFormsService
): ConjunctionsService {

    @Throws(Exception::class)
    override suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction> {
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

        return listOf(VerbConjunction(verb, Tense.Past, forms))
    }

}