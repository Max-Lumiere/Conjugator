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
