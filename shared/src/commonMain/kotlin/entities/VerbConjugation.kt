package entities

data class VerbConjugation(val verb: Verb,
                           val tense: Tense,
                           val forms: List<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VerbConjugation

        if (verb != other.verb) return false
        if (tense != other.tense) return false
        if (forms != other.forms) return false

        return true
    }

    override fun hashCode(): Int {
        var result = verb.hashCode()
        result = 31 * result + tense.hashCode()
        result = 31 * result + forms.hashCode()
        return result
    }
}
