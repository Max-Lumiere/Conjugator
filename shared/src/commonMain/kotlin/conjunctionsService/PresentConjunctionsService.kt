package conjunctionsService

import entities.Tense
import entities.Verb
import entities.VerbConjunction
import verbFormsService.VerbFormsService

class PresentConjunctionsService(
    private val aTypeService: VerbFormsService,
    private val iTypeService: VerbFormsService,
    private val oTypeService: VerbFormsService,
    private val reflexiveATypeService: VerbFormsService,
    private val reflexiveITypeService: VerbFormsService,
    private val reflexiveOTypeService: VerbFormsService,
    private val butiService: VerbFormsService
    ): ConjunctionsService {

    @Throws(Exception::class)
    override suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction> {
        val forms: List<String>

        if (verb.present == "yra") {
            forms =  butiService.getVerbFormsFor(verb.present)
        } else if (verb.present.endsWith("si")) {
            forms = when (verb.present.dropLast(2).last()) {
                'a' -> reflexiveATypeService.getVerbFormsFor(verb.present)
                'i' -> reflexiveITypeService.getVerbFormsFor(verb.present)
                'o' -> reflexiveOTypeService.getVerbFormsFor(verb.present)
                else -> {
                    throw Exception("Wrong present form ${verb.present}")
                }
            }
        } else {
            forms = when (verb.present.last()) {
                'a' -> aTypeService.getVerbFormsFor(verb.present)
                'i' -> iTypeService.getVerbFormsFor(verb.present)
                'o' -> oTypeService.getVerbFormsFor(verb.present)
                else -> {
                    throw Exception("Wrong present form ${verb.present}")
                }
            }
        }

        return listOf(VerbConjunction(verb, Tense.Present, forms))
    }

}