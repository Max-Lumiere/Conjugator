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
