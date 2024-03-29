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

import entities.DTO.JSON.VerbConjugationJSON
import entities.Verb
import entities.VerbConjugation
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConjugationIntegrationTests {

    var sut: Context? = null

    @BeforeTest
    fun setUp() {
        sut = Context()
    }

    @Test
    fun testConjugations() = runTest {
        val localConjugationsMap = Json.decodeFromString<Map<String, List<VerbConjugation>>>(
            localVerbConjugations
        )

        for (key in localConjugationsMap.keys) {
            val localConjugations = localConjugationsMap[key]
            val verb = localConjugations!![0].verb
            val calculatedConjugations = sut!!.conjugationsService.getConjugationsFor(verb)

            for (conjugation in localConjugations) {
                val calculated = calculatedConjugations.first { it.tense == conjugation.tense }

                assertEquals(conjugation, calculated)
            }
        }
    }

    @Test
    fun testGenerate() = runTest {
        // bučiúotis, bučiúojasi, bučiãvosi
        val strings = listOf(
            "draustis, draudžiasi, draudėsi",
            "duoti, duoda, davė",
            "džiaugtis, džiaugiasi, džiaugėsi",
            "džiauti, džiauna, džiovė"
        )
        val map: MutableMap<String, List<VerbConjugationJSON>> = HashMap<String, List<VerbConjugationJSON>>().toMutableMap()

        for (string in strings) {
            val verb = verbFromString(string)
            val conjugation = sut!!.conjugationsService.getConjugationsFor(verb)
                .map { VerbConjugationJSON(it) }

            map[verb.infinitive] = conjugation
        }

        println("=======")
        println(Json.encodeToString(serializer(), map))
    }

    fun verbFromString(str: String): Verb {
        val forms = str
            .replace(" ","")
            .split(",")
        return Verb(forms[0], forms[1], forms[2])
    }
}