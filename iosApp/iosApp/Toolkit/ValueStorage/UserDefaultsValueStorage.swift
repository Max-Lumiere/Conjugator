import Foundation

final class UserDefaultsValueStorage<Value: Codable>: ValueStorage {
    private let userDefaults: UserDefaults
    private let key: String

    init(userDefaults: UserDefaults, key: String) {
        self.userDefaults = userDefaults
        self.key = key
    }

    func write(_ value: Value?) {
        userDefaults.set(try? PropertyListEncoder().encode([value]), forKey: key)
        userDefaults.synchronize()
    }

    func read() -> Value? {
        userDefaults.data(forKey: key).flatMap { try? PropertyListDecoder().decode(Array<Value>.self, from: $0).first }
    }
}
