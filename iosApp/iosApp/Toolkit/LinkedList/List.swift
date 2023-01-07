import Foundation

struct List<Value> {
    private(set) var first: Node?
    private(set) var last: Node?

    @discardableResult
    mutating func append(_ value: Value) -> Node {
        let node = Node(value: value)
        node.previous = last
        last?.next = node
        last = node
        if first == nil {
            first = node
        }
        return node
    }

    mutating func remove(_ node: Node) {
        node.previous?.next = node.next
        node.next?.previous = node.previous
        if node === first {
            first = node.next
        }
        if node === last {
            last = node.previous
        }
    }
}

extension List {
    class Node {
        var value: Value
        fileprivate(set) weak var previous: Node?
        fileprivate(set) var next: Node?

        init(value: Value) {
            self.value = value
        }
    }
}

extension List: Sequence {
    func makeIterator() -> AnyIterator<Value> {
        var note = first
        return AnyIterator {
            let value = note?.value
            note = note?.next
            return value
        }
    }
}
