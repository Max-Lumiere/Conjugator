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

import verbFormsService.ETypeFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ETypeFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ETypeFormsService()
    }

    @Test
    fun test_e() {
        val form = "griaudė"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("griaudžiau", "griaudei", "griaudė", "griaudėme", "griaudėte", "griaudė")
        )
    }

    @Test
    fun test_te() {
        val form = "švietė"

        assertEquals(
            sut!!.getVerbFormsFor(form),
            listOf("šviečiau", "švietei", "švietė", "švietėme", "švietėte", "švietė")
        )
    }
}