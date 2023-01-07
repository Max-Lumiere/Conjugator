import Foundation

@propertyWrapper
class AtomicProperty<T> {
    private var property: T
    var wrappedValue: T {
        get { lock.sync { property } }
        set {
            lock.async(flags: .barrier) { [weak self] in
                self?.property = newValue
            }
        }
    }

    private let lock: DispatchQueue = {
        var name = "atomic_prop_\(Int.random(in: 0...100000))_\(T.self)"
        return DispatchQueue(label: name, attributes: .concurrent)
    }()

    init(wrappedValue value: T) {
        self.property = value
    }
}
