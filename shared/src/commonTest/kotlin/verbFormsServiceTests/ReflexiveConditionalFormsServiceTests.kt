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

import verbFormsService.ReflexiveConditionalFormsService
import verbFormsService.VerbFormsService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReflexiveConditionalFormsServiceTests: VerbFormsServiceTests() {
    private var verbFormsMock: VerbFormsServiceMock = VerbFormsServiceMock()

    @BeforeTest
    fun setUp() {
        verbFormsMock = VerbFormsServiceMock()
        sut = ReflexiveConditionalFormsService(verbFormsMock)
        verbFormsMock.result = listOf(
            "mokyčiau",
            "mokytum",
            "mokytų",
            "mokytume",
            "mokytume",
            "mokytų"
        )
    }

    @Test
    fun test_conjugation() {
        assertEquals(listOf(
            "mokyčiausi",
            "mokytumeisi",
            "mokytųsi",
            "mokytumėmės",
            "mokytumėtės",
            "mokytųsi"
        ), sut!!.getVerbFormsFor("mokytis"))
        assertEquals(1, verbFormsMock.getFormsCount)
    }

    @Test
    fun test_passed_parameter() {
        sut!!.getVerbFormsFor("mokytis")
        assertEquals("mokyti", verbFormsMock.lastFormPassed)
        assertEquals(1, verbFormsMock.getFormsCount)
    }
}