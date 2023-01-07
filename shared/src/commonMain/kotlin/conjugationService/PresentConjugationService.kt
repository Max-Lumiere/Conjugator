package conjugationService
import entities.Verb
import entities.VerbConjunction
import kotlin.coroutines.cancellation.CancellationException

class PresentConjugationService: ConjugationService {
    val firstTypeService: ConjugationService
    val secondTypeService: ConjugationService
    val thirdTypeService: ConjugationService

    constructor(firstTypeService: ConjugationService,
                secondTypeService: ConjugationService,
                thirdTypeService: ConjugationService) {
        this.firstTypeService = firstTypeService
        this.secondTypeService = secondTypeService
        this.thirdTypeService = thirdTypeService
    }

    @Throws(Exception::class)
    override fun getConjunctionFor(verb: Verb): VerbConjunction {
        val present = verb.present

        return when (present.last()) {
            `a` -> firstTypeService.getConjunctionFor(verb)
            `i` -> secondTypeService.getConjunctionFor(verb)
            `o` -> thirdTypeService.getConjunctionFor(verb)
            else -> {
                throw Exception("Wrong present from ${verb.present} of verb ${verb.infinitive}")
            }
        }
    }
}