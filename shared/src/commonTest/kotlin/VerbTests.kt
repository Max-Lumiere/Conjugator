import entities.Verb
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class VerbTests {

    @Test
    fun testEquals_true() {
        val verb1 = Verb("test1", "test2", "test3")
        val verb2 = Verb("test1", "test2", "test3")

        assertEquals(verb1, verb2)
    }

    @Test
    fun testEquals_false() {
        val verb1 = Verb("test1", "test2", "test3")
        val verb2 = Verb("test11", "test22", "test33")

        assertNotEquals(verb1, verb2)
    }
}