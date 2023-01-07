struct Lens<Whole, Part> {
    let get: (Whole) -> Part
    let set: (Part) -> (Whole) -> Whole

    init(get: @escaping (Whole) -> Part, set: @escaping (Part) -> (Whole) -> Whole) {
        self.get = get
        self.set = set
    }
}
