import Foundation

final class Atomic<Value> {
    private let lock = NSLock()
    private var _value: Value

    var value: Value {
        get {
            lock.lock()
            defer { lock.unlock() }
            return _value
        }
        set {
            lock.lock()
            _value = newValue
            lock.unlock()
        }
    }

    init(_ value: Value) {
        _value = value
    }
}
