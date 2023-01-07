import Foundation

enum MemoryLeakChecker {
    static func checkOnLeaks(_ target: AnyObject) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) { [weak target] in
            guard let target = target else { return }

            #if DEBUG
            fatalError("[MEMORY_LEAK] \(target)")
            #else
            print("[MEMORY_LEAK] \(target)")
            #endif
        }
    }
}
