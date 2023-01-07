import Foundation
import UIKit

protocol NibBasedView: AnyObject {
    static var nibName: String { get }
    static var nib: UINib { get }
}

protocol ReusableView: AnyObject {
    static var reuseIdentifier: String { get }
}

extension ReusableView where Self: NibBasedView {
    static var reuseIdentifier: String { nibName }
}

extension NibBasedView where Self: UIView {
    static var nibName: String {
        return "\(self)"
    }

    static var nib: UINib {
        let bundle = Bundle(for: Self.self)
        return UINib(nibName: self.nibName, bundle: bundle)
    }
}

extension NibBasedView where Self: UIView {
    static func instantiateFromNib(bundle: Bundle? = nil) -> Self? {
        UINib(nibName: nibName, bundle: bundle ?? Bundle(for: Self.self))
            .instantiate(withOwner: nil, options: nil)
            .filter { result in (result as? Self) != nil }
            .first as? Self
    }
}

extension UIView {
    func addToView(_ otherView: UIView) {
        otherView.addSubview(self)
        self.translatesAutoresizingMaskIntoConstraints = false
        self.topAnchor.constraint(equalTo: otherView.topAnchor, constant: 0).isActive = true
        self.leftAnchor.constraint(equalTo: otherView.leftAnchor, constant: 0).isActive = true
        self.rightAnchor.constraint(equalTo: otherView.rightAnchor, constant: 0).isActive = true
        self.bottomAnchor.constraint(equalTo: otherView.bottomAnchor, constant: 0).isActive = true
    }
}

extension UITableView {

    func cell<T: UITableViewCell>(at indexPath: IndexPath) -> T? where T: ReusableView {
        cellForRow(at: indexPath) as? T
    }

    func register(_ type: (NibBasedView & ReusableView).Type) {
        register(type.nib, forCellReuseIdentifier: type.reuseIdentifier)
    }

    func dequeueReusableCell<T: UITableViewCell>(_ type: T.Type, for indexPath: IndexPath) -> T where T: ReusableView {
        if let cell = dequeueReusableCell(withIdentifier: type.reuseIdentifier, for: indexPath) as? T {
            return cell
        }
        fatalError("Wrong type of cell for asked \(type)")
    }
}

extension UICollectionView {

    func cell<T: UICollectionViewCell>(at indexPath: IndexPath) -> T? where T: ReusableView {
        cellForItem(at: indexPath) as? T
    }

    func register<T: ReusableView>(_ type: T.Type) where T: NibBasedView {
        register(type.nib, forCellWithReuseIdentifier: type.reuseIdentifier)
    }

    func register<T: ReusableView>(_ type: T.Type) {
        register(type.self, forCellWithReuseIdentifier: type.reuseIdentifier)
    }

    func dequeueReusableCell<T: UICollectionViewCell>(_ type: T.Type,
                                                      for indexPath: IndexPath) -> T where T: ReusableView {
        if let cell = dequeueReusableCell(withReuseIdentifier: type.reuseIdentifier, for: indexPath) as? T {
            return cell
        }
        fatalError("Wrong type of cell for asked \(type)")
    }
}
