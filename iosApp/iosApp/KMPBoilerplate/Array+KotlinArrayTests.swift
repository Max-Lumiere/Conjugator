//
//  Created by Maksim Sviatlou on 18.01.23.

@testable import iosApp
import shared
import Quick
import Nimble

final class Array_KotlinArrayTests: QuickSpec {

    override func spec() {
        describe("kotlin array transformation") {
            context("when transform kotlin array to array") {
                it("works fine") {
                    let kotlinArray: KotlinArray<NSNumber> = KotlinArray(size: 10) { index in index }
                    let array = Array(kotlinArray).map { $0.intValue }

                    expect(array).to(equal([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]))
                }
            }
        }
    }

}
