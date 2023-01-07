import Foundation

struct IndexedList<Key: Hashable, Value> {
    typealias Pair = (key: Key, value: Value)
    private var list = List<Pair>()
    private var indexes = [Key: List<Pair>.Node]()

    mutating func add(_ key: Key, value: Value) {
        indexes[key] = list.append((key, value))
    }

    mutating func remove(_ key: Key) {
        guard let node = indexes.removeValue(forKey: key) else { return }
        list.remove(node)
    }

    mutating func removeAll() {
        list = .init()
        indexes = .init()
    }

    func contains(_ key: Key) -> Bool {
        indexes[key] != nil
    }

    func toArray() -> [Pair] {
        Array(list)
    }

    subscript(key: Key) -> Value? {
        get {
            indexes[key]?.value.value
        }
        set(newValue) {
            if let newValue = newValue {
                add(key, value: newValue)
            } else {
                remove(key)
            }
        }
    }
}

extension IndexedList: Sequence {
    func makeIterator() -> AnyIterator<Pair> {
        list.makeIterator()
    }
}
