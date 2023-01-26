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
//  Created by Maksim Sviatlou on 7.01.23.

import Foundation
import Combine
import shared

extension VerbInput {

    final class ViewModel {
        private var cancellables = Set<AnyCancellable>()
        private var infinitive: String?
        private var present: String?
        private var past: String?

        let verbPublisher: AnyPublisher<Verb, Never>
        private let verbSubject = PassthroughSubject<Verb, Never>()

        let infinitivePublisher: AnyPublisher<String?, Never>
        private let infinitiveSubject = CurrentValueSubject<String?, Never>("eiti")

        let presentPublisher: AnyPublisher<String?, Never>
        private let presentSubject = CurrentValueSubject<String?, Never>("eina")

        let pastPublisher: AnyPublisher<String?, Never>
        private let pastSubject = CurrentValueSubject<String?, Never>("ėjo")

        let infinitiveFailurePublisher: AnyPublisher<Bool, Never>
        private let infinitiveFailureSubject = PassthroughSubject<Bool, Never>()

        let presentFailurePublisher: AnyPublisher<Bool, Never>
        private let presentFailureSubject = PassthroughSubject<Bool, Never>()

        let pastFailurePublisher: AnyPublisher<Bool, Never>
        private let pastFailureSubject = PassthroughSubject<Bool, Never>()

        let infinitiveActivationPublisher: AnyPublisher<Bool, Never>
        private let infinitiveActivationSubject = PassthroughSubject<Bool, Never>()

        let presentActivationPublisher: AnyPublisher<Bool, Never>
        private let presentActivationSubject = PassthroughSubject<Bool, Never>()

        let pastActivationPublisher: AnyPublisher<Bool, Never>
        private let pastActivationSubject = PassthroughSubject<Bool, Never>()

        init() {
            verbPublisher = verbSubject.eraseToAnyPublisher()
            infinitiveFailurePublisher = infinitiveFailureSubject.eraseToAnyPublisher()
            presentFailurePublisher = presentFailureSubject.eraseToAnyPublisher()
            pastFailurePublisher = pastFailureSubject.eraseToAnyPublisher()
            infinitiveActivationPublisher = infinitiveActivationSubject.eraseToAnyPublisher()
            presentActivationPublisher = presentActivationSubject.eraseToAnyPublisher()
            pastActivationPublisher = pastActivationSubject.eraseToAnyPublisher()
            infinitivePublisher = infinitiveSubject.removeDuplicates().eraseToAnyPublisher()
            presentPublisher = presentSubject.removeDuplicates().eraseToAnyPublisher()
            pastPublisher = pastSubject.removeDuplicates().eraseToAnyPublisher()
        }
        
        func set(infinitive: String?) {
            guard infinitive != self.infinitive else { return }
            self.infinitive = infinitive
            infinitiveFailureSubject.send(false)
            infinitiveSubject.send(infinitive)
        }

        func set(present: String?) {
            guard present != self.present else { return }
            self.present = present
            presentFailureSubject.send(false)
            presentSubject.send(present)
        }

        func set(past: String?) {
            guard past != self.past else { return }
            self.past = past
            pastFailureSubject.send(false)
            pastSubject.send(past)
        }

        func infinitiveReturn() {
            presentActivationSubject.send(true)
        }

        func presentReturn() {
            pastActivationSubject.send(true)
        }

        func pastReturn() {
            conjure()
        }

        func conjure() {
            infinitiveActivationSubject.send(false)
            presentActivationSubject.send(false)
            pastActivationSubject.send(false)
            guard let infinitive, let present, let past, !infinitive.isEmpty, !present.isEmpty, !past.isEmpty else {
                infinitiveFailureSubject.send(infinitive?.isEmpty ?? true)
                presentFailureSubject.send(present?.isEmpty ?? true)
                pastFailureSubject.send(past?.isEmpty ?? true)
                return
            }

            verbSubject.send(Verb(infinitive: infinitive, present: present, past: past))
        }
    }
}
