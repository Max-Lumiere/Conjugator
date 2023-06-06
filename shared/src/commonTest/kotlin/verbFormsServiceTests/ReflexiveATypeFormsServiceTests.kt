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

import verbFormsService.ReflexiveATypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveATypeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ReflexiveATypeFormsService()
    }

    @Test
    fun test_a() {
        assertEquals(
            sut!!.getVerbFormsFor("nešasi"),
            listOf("nešuosi", "nešiesi", "nešasi", "nešamės", "nešatės", "nešasi")
        )
    }

    @Test
    fun test_ia() {
        assertEquals(
            sut!!.getVerbFormsFor("veikiasi"),
            listOf("veikiuosi", "veikiesi", "veikiasi", "veikiamės", "veikiatės", "veikiasi")
        )
    }

    @Test
    fun test_dzia() {
        assertEquals(
            sut!!.getVerbFormsFor("draudžiasi"),
            listOf(
                "draudžiuosi",
                "draudiesi",
                "draudžiasi",
                "draudžiamės",
                "draudžiatės",
                "draudžiasi"
            )
        )
    }
}