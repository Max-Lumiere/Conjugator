package entities

data class VerbConjunction(val verb: Verb,
                           val tense: Tense,
                           val forms: Array<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        return hashCode() == other.hashCode()
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || this::class != other::class) return false
//
//        other as VerbConjunction
//
//        if (verb != other.verb) return false
//        if (!forms.contentEquals(other.forms)) return false
//
//        return true
//    }

    override fun hashCode(): Int {
        var result = verb.hashCode()
        result = 31 * result + tense.hashCode()
        result = 31 * result + forms.contentHashCode()
        return result
    }
}
