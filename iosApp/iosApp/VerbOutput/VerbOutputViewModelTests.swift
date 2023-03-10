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
    
@testable import Conjugator
import shared
import Quick
import Nimble
import Combine

final class VerbOutputViewModelTests: QuickSpec {
    private enum Error: Swift.Error {
        case error
    }

    private class TenseLocalizationServiceMock: TenseLocalizationService {
        var result = ""

        func localizationFor(tense: Tense) -> String { result }
    }

    private class ConjugationsServiceMock: ConjugationsService {
        var error: Error?
        var result: [VerbConjugation]?

        func getConjugationsFor(verb: Verb,
                                completionHandler: @escaping ([VerbConjugation]?, Swift.Error?) -> Void) {
            DispatchQueue.main.async {
                completionHandler(self.result, self.error)
            }
        }
    }

    override func spec() {
        describe("Verb output view model") {
            var cancellables = Set<AnyCancellable>()
            var sut: VerbOutput.ViewModel!
            var conjugationsService: ConjugationsServiceMock!
            var tenseLocalizationService: TenseLocalizationServiceMock!
            let verb = Verb(infinitive: "", present: "", past: "")

            beforeEach {
                conjugationsService = ConjugationsServiceMock()
                tenseLocalizationService = TenseLocalizationServiceMock()
                sut = VerbOutput.ViewModel(conjugationsService: conjugationsService,
                                           tenseLocalizationService: tenseLocalizationService,
                                           verb: verb)
            }

            context("when close is called") {
                it("sends close signal") {
                    var closed = false

                    sut.onClose
                        .sink { closed = true }
                        .store(in: &cancellables)

                    sut.close()

                    expect(closed).to(beTrue())
                }
            }

            context("when conjunction works fine") {
                it("sends localized conjugations") {
                    var items: [VerbOutput.Item]?
                    let forms = Array(repeatElement("qwerty", count: 6))
                    let tense = "zxcvb"

                    tenseLocalizationService.result = tense
                    conjugationsService.result = [VerbConjugation(verb: verb,
                                                                  tense: .present,
                                                                  forms: forms)]
                    sut.onItems
                        .sink { items = $0 }
                        .store(in: &cancellables)

                    sut.conjure()

                    expect(items).toEventually(equal([VerbOutput.Item(forms: forms, tense: tense)]))
                }
            }

            context("when conjunction doesn't work") {
                it("sends error") {
                    var error: Swift.Error?

                    conjugationsService.error = Error.error
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
