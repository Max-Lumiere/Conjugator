//
//  Created by Maksim Sviatlou on 18.01.23.
    
@testable import iosApp
import shared
import Quick
import Nimble
import Combine

final class VerbOutputViewModelTests: QuickSpec {
    private enum Error: Swift.Error {
        case error
    }

    private class ConjunctionsServiceMock: ConjunctionsService {
        var error: Error?
        var result: [VerbConjunction]?

        func getConjunctionsFor(verb: Verb,
                                completionHandler: @escaping ([VerbConjunction]?, Swift.Error?) -> Void) {
            DispatchQueue.main.async {
                completionHandler(self.result, self.error)
            }
        }
    }

    override func spec() {
        describe("Verb output view model") {
            var cancellables = Set<AnyCancellable>()
            var sut: VerbOutput.ViewModel!
            var conjunctionsService: ConjunctionsServiceMock!
            let verb = Verb(infinitive: "", present: "", past: "")

            beforeEach {
                conjunctionsService = ConjunctionsServiceMock()
                sut = VerbOutput.ViewModel(conjunctionsService: conjunctionsService, verb: verb)
            }

            context("when conjunction works fine") {
                it("send conjunctions") {
                    var conjunctions: [VerbConjunction]?

                    conjunctionsService.result = []
                    sut.onConjunctions
                        .sink { conjunctions = $0 }
                        .store(in: &cancellables)

                    sut.conjure()

                    expect(conjunctions).toEventuallyNot(beNil())
                }
            }

            context("when conjunction doesn't work") {
                it("sends error") {
                    var error: Swift.Error?

                    conjunctionsService.error = Error.error
                    sut.onError
                        .sink { error = $0 }
                        .store(in: &cancellables)

                    sut.conjure()

                    expect(error).toEventuallyNot(beNil())
                }
            }

            context("when conjunction doesn't return nor result, nor error") {
                it("sends error") {
                    var error: Swift.Error?

                    sut.onError
                        .sink { error = $0 }
                        .store(in: &cancellables)

                    sut.conjure()

                    expect(error).toEventuallyNot(beNil())
                }
            }
        }
    }

}
