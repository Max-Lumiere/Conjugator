//
//  Created by Maksim Sviatlou on 23.01.23.
    

@testable import iosApp
import Quick
import Nimble
import Combine

final class VerbOutputViewControllerTests: QuickSpec {

    override func spec() {
        describe("VerbOutputViewController") {
            var cancellables = Set<AnyCancellable>()
            var sut: VerbOutputViewController!
            let items: [VerbOutput.Item] = [.init(forms: Array(repeatElement("1", count: 6)), tense: "tense1"),
                                            .init(forms: Array(repeatElement("2", count: 6)), tense: "tense2"),
                                            .init(forms: Array(repeatElement("3", count: 6)), tense: "tense3")]

            beforeEach {
                sut = VerbOutputViewController(nibName: nil, bundle: Bundle(for: VerbOutputViewController.self))
                _ = sut.view
            }

            context("when set items") {
                it("updates table view") {

                    sut.set(items: items)

                    expect(sut.tableView.cellForRow(at: .init(row: 0, section: 0))).toEventuallyNot(beNil())
                    expect(sut.tableView.cellForRow(at: .init(row: 1, section: 0))).toEventuallyNot(beNil())
                    expect(sut.tableView.cellForRow(at: .init(row: 2, section: 0))).toEventuallyNot(beNil())
                    expect(sut.tableView.cellForRow(at: .init(row: 3, section: 0))).toEventually(beNil())
                }
            }

            context("when press back button") {
                it("sends back signal") {
                    var backCalled = false

                    sut.onBack
                        .sink { backCalled = true }
                        .store(in: &cancellables)

                    sut.back()
                    expect(backCalled).to(beTrue())
                }
            }
        }
    }
}
