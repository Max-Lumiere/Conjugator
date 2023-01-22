package conjunctionsService

import entities.Verb
import entities.VerbConjunction

class CollectionConjunctionsService(
    private val innerServices: List<ConjunctionsService>
    ): ConjunctionsService {

    @Throws(Exception::class)
    override suspend fun getConjunctionsFor(verb: Verb): List<VerbConjunction> {
        val result = emptyList<VerbConjunction>().toMutableList()

        for (service in innerServices) {
            result.addAll(service.getConjunctionsFor(verb))
        }

        return result
    }
}