package conjugationService

import entities.Verb
import entities.VerbConjunction

interface ConjugationService {

    @Throws(Exception::class)
    fun getConjunctionFor(verb: Verb): VerbConjunction
}