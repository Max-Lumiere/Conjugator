package conjugationService
import entities.Verb
import entities.VerbConjunction

class PresentConjugationService(
    private val firstTypeService: ConjugationService,
    private val secondTypeService: ConjugationService,
    private val thirdTypeService: ConjugationService
) : ConjugationService {

    @Throws(Exception::class)
    override fun getConjunctionFor(verb: Verb): VerbConjunction {
        val present = verb.present

        return when (present.last()) {
            'a' -> firstTypeService.getConjunctionFor(verb)
            'i' -> secondTypeService.getConjunctionFor(verb)
            'o' -> thirdTypeService.getConjunctionFor(verb)
            else -> {
                throw Exception("Wrong present from ${verb.present} of verb ${verb.infinitive}")
            }
        }
    }
}