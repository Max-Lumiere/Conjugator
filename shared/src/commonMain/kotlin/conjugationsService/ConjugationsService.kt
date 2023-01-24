package conjugationsService

import entities.Verb
import entities.VerbConjugation

interface ConjugationsService {

    @Throws(Exception::class)
    suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation>
}