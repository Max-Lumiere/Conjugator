package entities

class Verb(val infinitive: String, val present: String, val past: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        println("[DEBUG] ${other is Verb}")
        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        var result = infinitive.hashCode()

        result = 31 * result + present.hashCode()
        result = 31 * result + past.hashCode()
        return result
    }
}