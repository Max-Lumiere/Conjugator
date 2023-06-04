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

import verbFormsService.ButiPresentVerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ButiPresentVerbFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ButiPresentVerbFormsService()
    }

    @Test
    fun test_buti() {
        val forms = sut!!.getVerbFormsFor("yra")

        assertContentEquals(forms, listOf(
            "esu / būnu",
            "esi / būni",
            "yra / būna",
            "esame / būname",
            "esate / būnate",
            "yra / būna"
        ))
    }
}