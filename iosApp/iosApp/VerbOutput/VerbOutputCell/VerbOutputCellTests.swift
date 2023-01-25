//
//  Created by Maksim Sviatlou on 23.01.23.
    

@testable import iosApp
import LumiereToolkit
import Combine
import Quick
import Nimble

final class VerbOutputCellTests: QuickSpec {

    override func spec() {
        describe("VerbOutputCell") {
            var sut: VerbOutputCell!
            let tense = "Present"
            let forms = ["einu", "eini", "eina", "einame", "einate", "eina"]

            beforeEach {
                sut = VerbOutputCell.instantiateFromNib()
            }

            context("when cell is created") {
                it("is expected that labels are empty") {
                    expect(sut.tenseLabel.text).to(beNil())
                    expect(sut.formLabels.compactMap { $0.text }).to(beEmpty())
                }
            }

            context("when set forms") {
                it("set it in right order") {
                    sut.set(forms: forms)

                    expect(sut.formLabels.map { $0.text }).to(equal(forms))
                }
            }

            context("when set tense") {
                it("updates title") {
                    sut.set(tense: tense)

                    expect(sut.tenseLabel.text).to(equal(tense))
                }
            }

            context("when prepare for resue") {
                it("cleans all labels") {
                    sut.set(tense: tense)
                    sut.set(forms: forms)

                    sut.prepareForReuse()

                    expect(sut.tenseLabel.text).to(beNil())
                    expect(sut.formLabels.compactMap { $0.text }).to(beEmpty())
                }
            }
        }
    }
}

