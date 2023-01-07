import UIKit

final class XFamilyLayoutConstraint: NSLayoutConstraint {
    @IBInspectable var specificConstant: CGFloat = 0 {
        didSet {
//            constant = DeviceModel.isXFamily ? specificConstant : constant
        }
    }
}
