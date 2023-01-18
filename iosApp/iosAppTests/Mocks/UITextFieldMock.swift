//
//  Created by Maksim Sviatlou on 11.01.23.

import UIKit

class UITextFieldMock: UITextField {

    override func becomeFirstResponder() -> Bool {
        _isFirstResponder = true
        return true
    }

    override func resignFirstResponder() -> Bool {
        _isFirstResponder = false
        return true
    }


    private var _isFirstResponder: Bool = false
    override var isFirstResponder: Bool { _isFirstResponder }
}
