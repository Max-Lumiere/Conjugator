package conjugationService

import entities.Verb
import entities.VerbConjunction

class FirstPresentConjugationService: ConjugationService {

    @Throws(Exception::class)
    override fun getConjunctionFor(verb: Verb): VerbConjunction {
        val present = verb.present
        var forms = Array<String>(6) { "" }

        forms[0] = present.dropLast(1) + `u`

        if (present[present.length - 2] == `i`) {
            forms[1] = present.dropLast(1)
        } else {
            forms[1] = present.dropLast(1) + `i`
        }

        forms[2] = present
        forms[3] = present + "me"
        forms[4] = present + "te"
        forms[5] = present

        return VerbConjunction(verb, forms)
    }
}