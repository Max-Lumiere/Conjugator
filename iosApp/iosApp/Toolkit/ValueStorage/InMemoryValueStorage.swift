import Foundation

final class InMemoryValueStorage<Value: Codable>: ValueStorage {
    private let lock = NSLock()
    private var value: Value?

    init(value: Value? = nil) {
        self.value = value
    }

    func write(_ value: Value?) {
        lock.lock()
        defer { lock.unlock() }
        self.value = value
    }

    func read() -> Value? {
        lock.lock()
        defer { lock.unlock() }
        return value
    }
}
