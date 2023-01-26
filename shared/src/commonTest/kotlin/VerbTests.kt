/*
 * This file is part of Conjugator.
 *
 * Conjugator is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation,
 * either version 3 of the License,
 * or (at your option) any later version.
 * Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Conjugator.
 * If not, see <https://www.gnu.org/licenses/>.
 */

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