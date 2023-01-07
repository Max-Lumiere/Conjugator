//@testable import
import Foundation
import Quick
import Nimble

final class FileValueStorageTests: QuickSpec {

    private typealias Storage = FileValueStorage

    override func spec() {
        let url = URL.cache.appendingPathComponent("\(Self.self)")
        var sut: Storage!

        beforeEach { sut = Storage(folder: url, name: UUID().uuidString) }

        describe("file value storage") {
            context("when there isn't stored data") {
                it("returns nil") {
                    expect(sut.read()).to(beNil())
                }
            }

            context("when there is stored data") {
                it("returns URL with corresponding data stored by it") {
                    let tempURL = URL.cache.appendingPathComponent(UUID().uuidString)

                    do {
                        let string = UUID().uuidString

                        try string.write(to: tempURL, atomically: true, encoding: .utf8)
                        sut.write(tempURL)

                        let storedUrl = sut.read()

                        expect(storedUrl).toNot(beNil())
                        expect(try String(contentsOf: storedUrl!, encoding: .utf8)).to(equal(string))
                    } catch {
                        fail("\(error.localizedDescription)")
                    }
                }
            }

            context("when data is deleted") {
                it("should return nil") {
                    let tempURL = URL.cache.appendingPathComponent(UUID().uuidString)

                    do {
                        try "qwerty".write(to: tempURL, atomically: true, encoding: .utf8)

                        sut.write(tempURL)
                        sut.write(nil)

                        expect(sut.read()).to(beNil())
                    } catch {
                        fail("\(error.localizedDescription)")
                    }
                }
            }
        }
    }
}
