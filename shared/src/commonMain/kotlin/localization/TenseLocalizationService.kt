package localization

import entities.Tense

interface TenseLocalizationService {

    fun localizationFor(tense: Tense): String
}