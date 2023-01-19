//
//  Created by Maksim Sviatlou on 7.01.23.

@testable import iosApp
import Combine
import Quick
import Nimble
import shared

final class VerbInputViewModelTests: QuickSpec {

    override func spec() {
        var sut: VerbInput.ViewModel!
        var cancellables = Set<AnyCancellable>()

        beforeEach {
            sut = VerbInput.ViewModel()
        }
        describe("Verb input view model") {

            context("when user presses next") {
                context("when infinitive is active") {
                    it("activates present") {
                        var isActive = false

                        sut.presentActivationPublisher.sink { isActive = $0 }.store(in: &cancellables)
                        sut.infinitiveReturn()

                        expect(isActive).to(beTrue())
                    }
                }

                context("when present is active") {
                    it("activates present") {
                        var isActive = false

                        sut.pastActivationPublisher.sink { isActive = $0 }.store(in: &cancellables)
                        sut.presentReturn()

                        expect(isActive).to(beTrue())
                    }
                }

                context("when past is active") {
                    it("conjures") {
                        var verb: Verb?

                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.set(infinitive: "test1")
                        sut.set(present: "test2")
                        sut.set(past: "test3")
                        sut.pastReturn()

                        expect(verb).toNot(beNil())
                    }
                }
            }

            context("when text changes") {
                it("resend infinitive") {
                    var infinitive: String?

                    sut.infinitivePublisher.sink { infinitive = $0 }.store(in: &cancellables)
                    sut.set(infinitive: "test")
                    expect(infinitive).to(equal("test"))
                }

                it("resend present") {
                    var present: String?

                    sut.presentPublisher.sink { present = $0 }.store(in: &cancellables)
                    sut.set(present: "test")
                    expect(present).to(equal("test"))
                }

                it("past present") {
                    var past: String?

                    sut.pastPublisher.sink { past = $0 }.store(in: &cancellables)
                    sut.set(past: "test")
                    expect(past).to(equal("test"))
                }

                context("when text is the duplicate") {
                    it("ignores it") {
                        let (infinitive, present, past) = ("infinitive", "present", "past")
                        var (infinitiveCount, presentCount, pastCount) = (0, 0, 0)

                        sut.infinitivePublisher.sink {
                            if $0 == infinitive {
                                infinitiveCount += 1
                            }

                        }.store(in: &cancellables)
                        sut.presentPublisher.sink {
                            if $0 == present {
                                presentCount += 1
                            }
                        }.store(in: &cancellables)
                        sut.pastPublisher.sink {
                            if $0 == past {
                                pastCount += 1
                            }
                        }.store(in: &cancellables)

                        sut.set(infinitive: infinitive)
                        sut.set(present: present)
                        sut.set(past: past)
                        sut.set(infinitive: infinitive)
                        sut.set(present: present)
                        sut.set(past: past)
                        sut.set(infinitive: infinitive)
                        sut.set(present: present)
                        sut.set(past: past)
                        sut.set(infinitive: infinitive)
                        sut.set(present: present)
                        sut.set(past: past)

                        expect(infinitiveCount).to(equal(1))
                        expect(presentCount).to(equal(1))
                        expect(pastCount).to(equal(1))
                    }
                }

            }

            context("when try to conjure") {
                context("when there are all forms") {
                    it("sends verb") {
                        var verb1 = Verb(infinitive: "", present: "", past: "")
                        let verb2 = Verb(infinitive: "test1", present: "test2", past: "test3")

                        sut.verbPublisher.sink { verb1 = $0 }.store(in: &cancellables)

                        sut.set(infinitive: verb2.infinitive)
                        sut.set(present: verb2.present)
                        sut.set(past: verb2.past)
                        sut.conjure()

                        expect(verb1).to(equal(verb2))
                    }
                }
                context("when infinitive is null") {
                    it("highlights infinitive") {
                        var isHighlighted = false

                        sut.infinitiveFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)

                        sut.conjure()

                        expect(isHighlighted).to(equal(true))
                    }
                }

                context("when infinitive is empty") {
                    it("highlights infinitive") {
                        var isHighlighted = false
                        var verb: Verb?

                        sut.infinitiveFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)
                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.set(infinitive: "")
                        sut.conjure()

                        expect(isHighlighted).to(equal(true))
                        expect(verb).to(beNil())
                    }
                }

                context("when present is null") {
                    it("highlights present") {
                        var isHighlighted = false
                        var verb: Verb?

                        sut.presentFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)
                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.conjure()

                        expect(isHighlighted).to(equal(true))
                        expect(verb).to(beNil())
                    }
                }

                context("when presentp is empty") {
                    it("highlights present") {
                        var isHighlighted = false
                        var verb: Verb?

                        sut.presentFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)
                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.set(present: "")
                        sut.conjure()

                        expect(isHighlighted).to(equal(true))
                        expect(verb).to(beNil())
                    }
                }

                context("when past is null") {
                    it("highlights past") {
                        var isHighlighted = false
                        var verb: Verb?

                        sut.pastFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)
                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.conjure()

                        expect(isHighlighted).to(beTrue())
                        expect(verb).to(beNil())
                    }
                }

                context("when present is empty") {
                    it("highlights past") {
                        var isHighlighted = false
                        var verb: Verb?

                        sut.pastFailurePublisher.sink { isHighlighted = $0 }.store(in: &cancellables)
                        sut.verbPublisher.sink { verb = $0 }.store(in: &cancellables)

                        sut.set(past: "")
                        sut.conjure()

                        expect(isHighlighted).to(equal(true))
                        expect(verb).to(beNil())
                    }
                }
            }
        }
    }
}
