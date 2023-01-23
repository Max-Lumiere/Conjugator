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