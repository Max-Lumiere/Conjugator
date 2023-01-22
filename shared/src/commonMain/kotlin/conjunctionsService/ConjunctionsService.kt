package conjunctionsService

import entities.Verb
import entities.VerbConjunction

interface ConjunctionsService {

    @Throws(Exception::class)
    suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction>
}