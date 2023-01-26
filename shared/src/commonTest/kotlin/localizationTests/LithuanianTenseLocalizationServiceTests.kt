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

package localizationTests

import entities.Tense
import localization.LithuanianTenseLocalizationService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class LithuanianTenseLocalizationServiceTests {

    var sut: LithuanianTenseLocalizationService? = null

    @BeforeTest
    fun setUp() {
        sut = LithuanianTenseLocalizationService()
    }

    @Test
    fun test_all_cases_apre_present() {
        for (tense in Tense.values()) {
            val result = sut!!.localizationFor(tense)

            assertFalse(result == "")
        }
    }
}