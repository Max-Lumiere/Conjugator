protocol ValueStorage {
    associatedtype Value: Codable

    func write(_ value: Value?)
    func read() -> Value?
}

final class AnyValueStorage<V: Codable>: ValueStorage {
    private let writeClosure: (V?) -> Void
    private let readClosure: () -> V?

    init<S: ValueStorage>(_ inner: S) where S.Value == V {
        writeClosure = inner.write(_:)
        readClosure = inner.read
    }

    func write(_ value: V?) { writeClosure(value) }
    func read() -> V? { readClosure() }
}
