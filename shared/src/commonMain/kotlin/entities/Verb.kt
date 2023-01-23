package entities

data class Verb(val infinitive: String, val present: String, val past: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Verb

        if (infinitive != other.infinitive) return false
        if (present != other.present) return false
        if (past != other.past) return false

        return true
    }

    override fun hashCode(): Int {
        var result = infinitive.hashCode()

        result = 31 * result + present.hashCode()
        result = 31 * result + past.hashCode()
        return result
    }
}