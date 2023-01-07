package conjugationService

import entities.Verb
import entities.VerbConjunction

class SecondPresentConjugationService: ConjugationService {

    override fun getConjunctionFor(verb: Verb): VerbConjunction {
        val present = verb.present
        val forms = Array(6) { "" }

        forms[0] = present + "u"
        forms[1] = present
        forms[2] = present
        forms[3] = present + "me"
        forms[4] = present + "te"
        forms[5] = present

        return VerbConjunction(verb, forms)
    }
}