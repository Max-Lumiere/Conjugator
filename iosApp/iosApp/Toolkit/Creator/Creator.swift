struct Creator<Argument, Type> {
    private let creation: (Argument) -> Type

    init(_ creation: @escaping (Argument) -> Type) {
        self.creation = creation
    }

    func create(with argument: Argument) -> Type {
        creation(argument)
    }
}

struct ThrowingCreator<Argument, Type> {
    private let creation: (Argument) throws -> Type

    init(_ creation: @escaping (Argument) throws -> Type) {
        self.creation = creation
    }

    func create(with argument: Argument) throws -> Type {
        try creation(argument)
    }
}

extension Creator where Argument == Void {

    func create() -> Type {
        create(with: ())
    }
}

extension ThrowingCreator where Argument == Void {

    func create() throws -> Type {
        try create(with: ())
    }
}
