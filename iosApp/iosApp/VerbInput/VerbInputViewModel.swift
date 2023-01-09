//
//  VerbInputViewModel.swift
//  iosApp
//
//  Created by Max Svetlov on 7.01.23.
//  Copyright © 2023 orgName. All rights reserved.
//

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

        let infinitiveFailurePublisher: AnyPublisher<Void?, Never>
        private let infinitiveFailureSubject = PassthroughSubject<Void?, Never>()

        let presentFailurePublisher: AnyPublisher<Void?, Never>
        private let presentFailureSubject = PassthroughSubject<Void?, Never>()

        let pastFailurePublisher: AnyPublisher<Void?, Never>
        private let pastFailureSubject = PassthroughSubject<Void?, Never>()

        init() {
            verbPublisher = verbSubject.eraseToAnyPublisher()
            infinitiveFailurePublisher = infinitiveFailureSubject.eraseToAnyPublisher()
            presentFailurePublisher = presentFailureSubject.eraseToAnyPublisher()
            pastFailurePublisher = pastFailureSubject.eraseToAnyPublisher()
        }
        
        func set(infinitive: String?) {
            self.infinitive = infinitive
            infinitiveFailureSubject.send(nil)
        }

        func set(present: String?) {
            self.present = present
            presentFailureSubject.send(nil)
        }

        func set(past: String?) {
            self.past = past
            pastFailureSubject.send(nil)
        }

        func conjure() {
            guard let infinitive = infinitive, let present = present, let past = past else {
                infinitiveFailureSubject.send(infinitive == nil ? () : nil)
                presentFailureSubject.send(present == nil ? () : nil)
                pastFailureSubject.send(past == nil ? () : nil)
                return
            }

            verbSubject.send(Verb(infinitive: infinitive, present: present, past: past))
        }
    }
}
