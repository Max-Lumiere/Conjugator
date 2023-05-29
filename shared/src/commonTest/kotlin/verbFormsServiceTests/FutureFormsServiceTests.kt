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

import verbFormsService.FutureFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FutureFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = FutureFormsService()
    }

    fun test_juoktis() { // unreal verb, test for reflexive corner case
        assertEquals(sut!!.getVerbFormsFor("juokti"), listOf(
            "juoksiu",
            "juoksi",
            "juoks",
            "juoksime",
            "juoksite",
            "juoks"
        ))
    }
    @Test
    fun test_common_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("tylėti"), listOf(
            "tylėsiu",
            "tylėsi",
            "tylės",
            "tylėsime",
            "tylėsite",
            "tylės"
        ))
    }

    @Test
    fun test_double_s_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("skristi"), listOf(
            "skrisiu",
            "skrisi",
            "skris",
            "skrisime",
            "skrisite",
            "skris"
        ))
    }

    @Test
    fun test_sh_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("nešti"), listOf(
            "nešiu",
            "neši",
            "neš",
            "nešime",
            "nešite",
            "neš"
        ))
    }

    @Test
    fun test_j_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("vežti"), listOf(
            "vešiu",
            "veši",
            "veš",
            "vešime",
            "vešite",
            "veš"
        ))
    }

    @Test
    fun test_z_conjugation() {
        assertEquals(sut!!.getVerbFormsFor("megzti"), listOf(
            "megsiu",
            "megsi",
            "megs",
            "megsime",
            "megsite",
            "megs"
        ))
    }
}