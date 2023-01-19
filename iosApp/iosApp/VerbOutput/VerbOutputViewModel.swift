//
//  Created by Maksim Sviatlou on 18.01.23.

import shared
import Combine

extension VerbOutput {

    final class ViewModel {
        private enum Error: Swift.Error {
            case conjunctionsError(Swift.Error)
            case unexpectedError
        }

        private let conjunctionsService: ConjunctionsService
        private let verb: Verb

        let onConjunctions: AnyPublisher<[VerbConjunction], Never>
        private let conjunctionsSubject = PassthroughSubject<[VerbConjunction], Never>()

        let onError: AnyPublisher<Swift.Error, Never>
        private let errorSubject = PassthroughSubject<Swift.Error, Never>()

        let onClose: AnyPublisher<Void, Never>
        private let closeSubject = PassthroughSubject<Void, Never>()

        init(conjunctionsService: ConjunctionsService, verb: Verb) {
            self.conjunctionsService = conjunctionsService
            self.verb = verb

            onConjunctions = conjunctionsSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onError = errorSubject.receive(on: DispatchQueue.main).eraseToAnyPublisher()
            onClose = closeSubject.eraseToAnyPublisher()
        }

        func conjure() {
            conjunctionsService.getConjunctionsFor(verb: verb) { [weak self] conjunctions, error in
                if let error = error {
                    self?.errorSubject.send(Error.conjunctionsError(error))
                } else if let conjunctions = conjunctions {
                    self?.conjunctionsSubject.send(Array(conjunctions))
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
