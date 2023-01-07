import UIKit

final class SELayoutConstraint: NSLayoutConstraint {
    @IBInspectable var specificConstant: CGFloat = 0 {
        didSet {
//            constant = DeviceModel.current == .iPhoneSE ? specificConstant : constant
        }
    }
}
