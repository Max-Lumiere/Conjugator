package localization

import entities.Tense

class LithuanianTenseLocalizationService: TenseLocalizationService {

    override fun localizationFor(tense: Tense): String {
        return when (tense) {
            Tense.Present -> "Esamasis laikas"
            Tense.Past -> "Būtasis kartinis laikas"
            Tense.PastContiniuos -> "Būtasis dažninis laikas"
            Tense.Conditional -> "Tariamoji nuosaka"
            Tense.Future -> "Būsimasis laikas"
            Tense.Imperative -> "Liepiamoji nuosaka"
        }
    }
}