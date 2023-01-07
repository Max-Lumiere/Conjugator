extension AnyIterator where Element: Numeric {

    static func fibonacci() -> AnyIterator<Element> {
        var index = 0
        var previous: Element = 0
        var current: Element = 1

        return AnyIterator {
            if index < 2 {
                defer { index += 1 }
                return Element(exactly: index)
            } else {
                let temp = current

                current += previous
                previous = temp
                return current
            }
        }
    }
}
