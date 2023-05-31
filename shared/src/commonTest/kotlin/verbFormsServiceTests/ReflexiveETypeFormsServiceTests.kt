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

package verbFormsServiceTests

import verbFormsService.ReflexiveETypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveETypeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ReflexiveETypeFormsService()
    }

    @Test
    fun test_esi() {
        val form = "rašėsi"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("rašiausi", "rašeisi", "rašėsi", "rašėmės", "rašėtės", "rašėsi")
        )
    }

    @Test
    fun test_tesi() {
        val form = "švietėsi"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("šviečiausi", "švieteisi", "švietėsi", "švietėmės", "švietėtės", "švietėsi")
        )
    }

    @Test
    fun test_desi() {
        val form = "maudėsi"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("maudžiausi", "maudeisi", "maudėsi", "maudėmės", "maudėtės", "maudėsi")
        )
    }
}