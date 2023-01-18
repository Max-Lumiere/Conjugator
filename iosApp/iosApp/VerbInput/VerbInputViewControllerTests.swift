//
//  VerbInputViewControllerTests.swift
//  iosApp
//
//  Created by Max Svetlov on 11.01.23.
//  Copyright © 2023 orgName. All rights reserved.
//

@testable import iosApp
import Combine
import Quick
import Nimble
import Cuckoo

//
//  Created by Maksim Sviatlou on 7.01.23.

final class VerbInputViewControllerTests: QuickSpec {

    private class ScrollViewMock: UIScrollView {
        var convertedRect: CGRect = .zero
        var setContentOffsetCalledCount = 0

        override func convert(_ rect: CGRect, from coordinateSpace: UICoordinateSpace) -> CGRect {
            convertedRect
        }

        override func setContentOffset(_ contentOffset: CGPoint, animated: Bool) {
            setContentOffsetCalledCount += 1
            return super.setContentOffset(contentOffset, animated: animated)
        }
    }

    override func spec() {
        var sut: VerbInputViewController!
        let (infinitive, present, past) = ("infinitive", "present", "past")
        var cancellables = Set<AnyCancellable>()
        var infinitiveTextField: UITextFieldMock!
        var presentTextField: UITextFieldMock!
        var pastTextField: UITextFieldMock!
        var scrollView: ScrollViewMock!

        beforeEach {
            scrollView = ScrollViewMock()
            infinitiveTextField = UITextFieldMock()
            presentTextField = UITextFieldMock()
            pastTextField = UITextFieldMock()
            sut = VerbInputViewController(nibName: nil, bundle: (Bundle(for: VerbInputViewController.self)))
            _ = sut.view
            sut.scrollView = scrollView
            sut.infinitiveTextField = infinitiveTextField
            sut.presentTextField = presentTextField
            sut.pastTextField = pastTextField
        }

        describe("VerbInputViewController tests") {
            context("when update text") {
                it("updates text in textfields") {
                    sut.set(infinitive: infinitive)
                    sut.set(present: present)
                    sut.set(past: past)

                    expect(sut.infinitiveTextField.text).to(equal(infinitive))
                    expect(sut.presentTextField.text).to(equal(present))
                    expect(sut.pastTextField.text).to(equal(past))
                }
            }

            context("when activate failures for text field") {
                it("responds") {
                    sut.setInfinitiveFailureHighlight(active: true)
                    sut.setPresentFailureHighlight(active: true)
                    sut.setPastFailureHighlight(active: true)

                    expect(sut.infinitiveTextField.layer.borderWidth).to(beGreaterThan(0))
                    expect(sut.presentTextField.layer.borderWidth).to(beGreaterThan(0))
                    expect(sut.pastTextField.layer.borderWidth).to(beGreaterThan(0))

                    sut.setInfinitiveFailureHighlight(active: false)
                    sut.setPresentFailureHighlight(active: false)
                    sut.setPastFailureHighlight(active: false)

                    expect(sut.infinitiveTextField.layer.borderWidth).to(equal(0))
                    expect(sut.presentTextField.layer.borderWidth).to(equal(0))
                    expect(sut.pastTextField.layer.borderWidth).to(equal(0))
                }
            }

            context("when button was pressed") {
                it("notifies subscribers") {
                    var isCalled = false

                    sut.conjurePublisher.sink { isCalled = true }.store(in: &cancellables)
                    sut.conjureButton.sendActions(for: .touchUpInside)

                    expect(isCalled).to(beTrue())
                }
            }

            context("when activate text field") {
                it("responds") {
                    expect(sut.infinitiveTextField.isFirstResponder).to(beFalse())

                    sut.setInfinitiveTextField(active: true)

                    expect(sut.infinitiveTextField.isFirstResponder).toEventually(beTrue())

                    sut.setInfinitiveTextField(active: false)

                    expect(sut.infinitiveTextField.isFirstResponder).to(beFalse())

                    sut.setPresentTextField(active: true)

                    expect(sut.presentTextField.isFirstResponder).to(beTrue())

                    sut.setPresentTextField(active: false)

                    expect(sut.presentTextField.isFirstResponder).to(beFalse())

                    sut.setPastTextField(active: true)

                    expect(sut.pastTextField.isFirstResponder).to(beTrue())

                    sut.setPastTextField(active: false)

                    expect(sut.pastTextField.isFirstResponder).to(beFalse())
                }
            }

            context("when return pressed") {
                context("when infinitive is active") {
                    it("sends return signal") {
                        var isCalled = false

                        sut.infinitiveReturnPublisher.sink { isCalled = true }.store(in: &cancellables)
                        _ = sut.textFieldShouldReturn(sut.infinitiveTextField)

                        expect(isCalled).to(beTrue())
                    }
                }

                context("when present is active") {
                    it("sends return signal") {
                        var isCalled = false

                        sut.presentReturnPublisher.sink { isCalled = true }.store(in: &cancellables)
                        _ = sut.textFieldShouldReturn(sut.presentTextField)

                        expect(isCalled).to(beTrue())
                    }
                }

                context("when past is active") {
                    it("sends return signal") {
                        var isCalled = false

                        sut.pastReturnPublisher.sink { isCalled = true }.store(in: &cancellables)
                        _ = sut.textFieldShouldReturn(sut.pastTextField)

                        expect(isCalled).to(beTrue())
                    }
                }

                context("when nothing is active") {
                    it("ignores") {
                        var isCalled = false

                        sut.infinitiveReturnPublisher.sink { isCalled = true }.store(in: &cancellables)
                        sut.presentReturnPublisher.sink { isCalled = true }.store(in: &cancellables)
                        sut.pastReturnPublisher.sink { isCalled = true }.store(in: &cancellables)

                        _ = sut.textFieldShouldReturn(UITextField())

                        expect(isCalled).to(beFalse())
                    }
                }
            }

            context("when keyboard appears") {
                context("if keyboard is visible") {
                    it("does nothing") {
                        NotificationCenter.default.post(name: UIResponder.keyboardDidShowNotification, object: nil)

                        expect(scrollView.setContentOffsetCalledCount).to(equal(0))
                    }
                }
                context("if keyboard covers button") {
                    it("scrolls to button") {
                        scrollView.convertedRect = CGRect(origin: .init(x: 0, y: 10000), size: .zero)

                        NotificationCenter.default.post(name: UIResponder.keyboardDidShowNotification, object: nil)

                        expect(scrollView.setContentOffsetCalledCount).to(equal(1))
                    }
                }
            }
        }

    }
}
