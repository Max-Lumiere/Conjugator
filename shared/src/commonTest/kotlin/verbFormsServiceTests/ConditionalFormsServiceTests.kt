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

import verbFormsService.ConditionalFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConditionalFormsServiceTests: VerbFormsServiceTests() {

    @BeforeTest
    fun setUp() {
        sut = ConditionalFormsService()
    }

    @Test
    fun test_conjugation() {
        assertEquals(listOf(
            "kalbėčiau",
            "kalbėtum",
            "kalbėtų",
            "kalbėtume / kalbėtumėme",
            "kalbėtute / kalbėtumėte",
            "kalbėtų"
        ), sut!!.getVerbFormsFor("kalbėti"))
    }

    @Test
    fun test_autis() {
        assertEquals(listOf(
            "aučiau",
            "autum",
            "autų",
            "autume / autumėme",
            "autute / autumėte",
            "autų"
        ), sut!!.getVerbFormsFor("auti"))
    }
}