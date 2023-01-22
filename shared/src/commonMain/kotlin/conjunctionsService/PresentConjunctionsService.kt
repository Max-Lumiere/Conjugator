package conjunctionsService

import entities.Tense
import entities.Verb
import entities.VerbConjunction
import verbFormsService.VerbFormsService

class PresentConjunctionsService(val verbFormsService: VerbFormsService): ConjunctionsService {

    override suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction> {
        val forms = verbFormsService.getVerbFormsFor(verb.present)
        val result =  VerbConjunction(verb, Tense.Present, forms)

        return listOf(result)
    }

}