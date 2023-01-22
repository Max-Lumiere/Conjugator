package conjunctionsService

import entities.Verb
import entities.VerbConjunction

class CollectionConjunctionsService(
    private val innerServices: Array<ConjunctionsService>
    ): ConjunctionsService {

    @Throws(Exception::class)
    override suspend fun getConjunctionsFor(verb: Verb): Array<VerbConjunction> {
        val result = emptyList<VerbConjunction>().toMutableList()

        for (service in innerServices) {
            result.addAll(service.getConjunctionsFor(verb))
        }

        return result.toTypedArray()
    }
}