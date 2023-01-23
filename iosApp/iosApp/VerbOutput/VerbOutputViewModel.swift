//
//  Created by Maksim Sviatlou on 18.01.23.

import shared
import Combine
import Foundation

extension VerbOutput {

    final class ViewModel {
        private enum Error: Swift.Error {
            case conjunctionsError(Swift.Error)
            case unexpectedError
        }

        private let tenseLocalizationService: TenseLocalizationService
        private let conjunctionsService: ConjunctionsService
        private let verb: Verb

        let onItems: AnyPublisher<[Item], Never>
        private let itemsSubject = PassthroughSubject<[Item], Never>()

        let onError: AnyPublisher<Swift.Error, Never>
        private let errorSubject = PassthroughSubject<Swift.Error, Never>()

        let onClose: AnyPublisher<Void, Never>
        private let closeSubject = PassthroughSubject<Void, Never>()

        init(conjunctionsService: ConjunctionsService, tenseLocalizationService: TenseLocalizationService ,verb: Verb) {
            self.conjunctionsService = conjunctionsService
            self.tenseLocalizationService = tenseLocalizationService
            self.verb = verb

            onItems = itemsSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onError = errorSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onClose = closeSubject.eraseToAnyPublisher()
        }

        func conjure() {
            conjunctionsService
                .getConjunctionsFor(verb: verb) { [weak self, tenseLocalizationService] conjunctions, error in
                    if let error = error {
                        self?.errorSubject.send(Error.conjunctionsError(error))
                    } else if let conjunctions = conjunctions {
                        self?.itemsSubject.send(conjunctions.map {
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
