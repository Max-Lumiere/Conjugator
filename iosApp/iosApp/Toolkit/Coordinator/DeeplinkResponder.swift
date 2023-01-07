
protocol DeepLinkResponder: AnyObject {
    func canHandleLink(_ code: String) -> Bool
    func handleLink(_ code: String)

    var isSelfSustained: Bool { get }
}
