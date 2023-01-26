//
// This file is part of Conjugator.

// Conjugator is free software:
// you can redistribute it and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation,
// either version 3 of the License,
// or (at your option) any later version.
// Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

// See the GNU General Public License for more details.
// You should have received a copy of the GNU General Public License along with Conjugator.
// If not, see <https://www.gnu.org/licenses/>.
//
//
//  Created by Maksim Sviatlou on 18.01.23.

import shared
import Combine
import Foundation

extension VerbOutput {

    final class ViewModel {
        private enum Error: Swift.Error {
            case conjugationsError(Swift.Error)
            case unexpectedError
        }

        private let tenseLocalizationService: TenseLocalizationService
        private let conjugationsService: ConjugationsService
        private let verb: Verb

        let onItems: AnyPublisher<[Item], Never>
        private let itemsSubject = PassthroughSubject<[Item], Never>()

        let onError: AnyPublisher<Swift.Error, Never>
        private let errorSubject = PassthroughSubject<Swift.Error, Never>()

        let onClose: AnyPublisher<Void, Never>
        private let closeSubject = PassthroughSubject<Void, Never>()

        init(conjugationsService: ConjugationsService, tenseLocalizationService: TenseLocalizationService ,verb: Verb) {
            self.conjugationsService = conjugationsService
            self.tenseLocalizationService = tenseLocalizationService
            self.verb = verb

            onItems = itemsSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onError = errorSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onClose = closeSubject.eraseToAnyPublisher()
        }

        func conjure() {
            conjugationsService
                .getConjugationsFor(verb: verb) { [weak self, tenseLocalizationService] conjugations, error in
                    if let error = error {
                        self?.errorSubject.send(Error.conjugationsError(error))
                    } else if let conjugations = conjugations {
                        self?.itemsSubject.send(conjugations.map {
                            Item(forms: $0.forms, tense: tenseLocalizationService.localizationFor(tense: $0.tense))
                        })
                    } else {
                        self?.errorSubject.send(Error.unexpectedError)
                    }
            }
        }

        func close() {
            closeSubject.send(())
        }
    }
}
