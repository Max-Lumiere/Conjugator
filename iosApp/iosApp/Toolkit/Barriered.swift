import Foundation

final class Barriered<Value> {
    private let queue = DispatchQueue(label: "com.wanna.barriered", attributes: .concurrent)
    private var _value: Value

    var value: Value {
        get { queue.sync { _value }}
        set {
            queue.async(flags: .barrier) { [weak self] in
                self?._value = newValue
            }
        }
    }

    init(_ value: Value) {
        _value = value
    }
}
