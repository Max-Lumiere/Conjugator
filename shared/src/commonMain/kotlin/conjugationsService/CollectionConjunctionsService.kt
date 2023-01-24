package conjugationsService

import entities.Verb
import entities.VerbConjugation

class CollectionConjugationsService(
    private val innerServices: List<ConjugationsService>
    ): ConjugationsService {

    @Throws(Exception::class)
    override suspend fun getConjugationsFor(verb: Verb): List<VerbConjugation> {
        val result = emptyList<VerbConjugation>().toMutableList()

        for (service in innerServices) {
            result.addAll(service.getConjugationsFor(verb))
        }

        return result
    }
}