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

package stringsProviderTests

import stringsProvider.StringsProvider
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StringsProviderTests {

    var sut: StringsProvider? = null

    @BeforeTest
    fun setUp() {
        sut = StringsProvider()
    }

    @Test
    fun test_get_string() {
        val str = "77, 87, 106, 64, 95, 77, 77, 77, 115, 84, 105, 66, 105, 73, 97, 74, 87, 73, 106, 98, 102, 127, 75, 75, 110, 107, 100, 95, 75, 72, 125, 69, 96, 76, 114, 97, 66, 93, 115, 110"

        assertEquals("JPmGXJJJtSnEnNfMPNmeaxLLilcXLOzBgKufEZti", sut!!.getStringFrom(str))
    }

    @Test
    fun test_make_string() {
        val str = "JPmGXJJJtSnEnNfMPNmeaxLLilcXLOzBgKufEZti"

        assertEquals(
            "[77, 87, 106, 64, 95, 77, 77, 77, 115, 84, 105, 66, 105, 73, 97, 74, 87, 73, 106, 98, 102, 127, 75, 75, 110, 107, 100, 95, 75, 72, 125, 69, 96, 76, 114, 97, 66, 93, 115, 110]",
            sut!!.makeStringFrom(str)
        )
    }

}