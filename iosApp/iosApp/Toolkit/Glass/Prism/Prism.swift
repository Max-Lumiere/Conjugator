struct Prism<Whole, Part> {
    let tryGet: (Whole) -> Part?
    let inject: (Part) -> Whole

    init(tryGet: @escaping (Whole) -> Part?, inject: @escaping (Part) -> Whole) {
        self.tryGet = tryGet
        self.inject = inject
    }
}
