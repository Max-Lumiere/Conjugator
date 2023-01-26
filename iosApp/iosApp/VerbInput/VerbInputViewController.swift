//
// This file is part of Conjugator.

// Conjugator is free software:
// you can redistribute it and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation,
// either version 3 of the License,
// or (at your option) any later version.
// Conjugator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

// See the GNU General Public License for more details.
// You should have received a copy of the GNU General Public License along with Conjugator.
// If not, see <https://www.gnu.org/licenses/>.
//
//
//  Created by Maksim Sviatlou on 7.01.23.

import UIKit
import Combine
import LumiereToolkit

private let scrollPadding: CGFloat = 10
private let textFieldFailedBorderWidth: CGFloat = 2

final class VerbInputViewController: ReactiveViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var infinitiveLabel: UILabel!
    @IBOutlet weak var presentLabel: UILabel!
    @IBOutlet weak var pastLabel: UILabel!
    @IBOutlet weak var infinitiveTextField: UITextField!
    @IBOutlet weak var presentTextField: UITextField!
    @IBOutlet weak var pastTextField: UITextField!
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var stackView: UIStackView!
    @IBOutlet weak var conjureButton: UIButton!

    private var cancellables = Set<AnyCancellable>()

    let infivitivePublisher: AnyPublisher<String?, Never>
    private let infinitiveSubject = PassthroughSubject<String?, Never>()

    let presentPublisher: AnyPublisher<String?, Never>
    private let presentSubject = PassthroughSubject<String?, Never>()

    let pastPublisher: AnyPublisher<String?, Never>
    private let pastSubject = PassthroughSubject<String?, Never>()

    let conjurePublisher: AnyPublisher<Void, Never>
    private let conjureSubject = PassthroughSubject<Void, Never>()

    let infinitiveReturnPublisher: AnyPublisher<Void, Never>
    private let infinitiveReturnSubject = PassthroughSubject<Void, Never>()

    let presentReturnPublisher: AnyPublisher<Void, Never>
    private let presentReturnSubject = PassthroughSubject<Void, Never>()

    let pastReturnPublisher: AnyPublisher<Void, Never>
    private let pastReturnSubject = PassthroughSubject<Void, Never>()

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        infivitivePublisher = infinitiveSubject.eraseToAnyPublisher()
        presentPublisher = presentSubject.eraseToAnyPublisher()
        pastPublisher = pastSubject.eraseToAnyPublisher()
        conjurePublisher = conjureSubject.eraseToAnyPublisher()
        infinitiveReturnPublisher = infinitiveReturnSubject.eraseToAnyPublisher()
        presentReturnPublisher = presentReturnSubject.eraseToAnyPublisher()
        pastReturnPublisher = pastReturnSubject.eraseToAnyPublisher()
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }

    required init?(coder: NSCoder) { fatalError("init(coder:) has not been implemented") }

    override func viewDidLoad() {
        super.viewDidLoad()

        infinitiveTextField.observe(\.text) { [weak self] textField, _ in
            self?.infinitiveSubject.send(textField.text)
        }.store(in: &cancellables)

        presentTextField.observe(\.text) { [weak self] textField, _ in
            self?.presentSubject.send(textField.text)
        }.store(in: &cancellables)

        pastTextField.observe(\.text) { [weak self] textField, _ in
            self?.pastSubject.send(textField.text)
        }.store(in: &cancellables)

        NotificationCenter.default.publisher(for: UIResponder.keyboardDidShowNotification).sink { [weak self] in
            self?.didShowKeyboard($0)
        }.store(in: &cancellables)
    }

    func set(infinitive: String?) {
        infinitiveTextField.text = infinitive
    }

    func set(present: String?) {
        presentTextField.text = present
    }

    func set(past: String?) {
        pastTextField.text = past
    }

    func setInfinitiveTextField(active: Bool) {
        set(active: active, textField: infinitiveTextField)
    }

    func setPresentTextField(active: Bool) {
        set(active: active, textField: presentTextField)
    }

    func setPastTextField(active: Bool) {
        set(active: active, textField: pastTextField)
    }

    func setInfinitiveFailureHighlight(active: Bool) {
        setFailureHighlight(active: active, for: infinitiveTextField)
    }

    func setPresentFailureHighlight(active: Bool) {
        setFailureHighlight(active: active, for: presentTextField)
    }

    func setPastFailureHighlight(active: Bool) {
        setFailureHighlight(active: active, for: pastTextField)
    }

    // MARK: - private

    @IBAction private func conjure(_ sender: Any) {
        conjureSubject.send(())
    }

    private func didShowKeyboard(_ notification: Notification) {
        let buttonFrame = scrollView.convert(conjureButton.bounds, from: conjureButton.coordinateSpace)
        let y = buttonFrame.maxY - scrollView.bounds.height + scrollPadding

        if !scrollView.bounds.contains(buttonFrame) {
            scrollView.setContentOffset(.init(x: 0, y: y), animated: true)
        }
    }

    private func set(active: Bool, textField: UITextField) {
        if active {
            textField.becomeFirstResponder()
        } else {
            textField.resignFirstResponder()
        }
    }

    private func setFailureHighlight(active: Bool, for textField: UITextField) {
        if active {
            textField.layer.borderColor = UIColor.orange.cgColor
            textField.layer.borderWidth = textFieldFailedBorderWidth
        } else {
            textField.layer.borderWidth = 0
        }
    }
}

extension VerbInputViewController: UITextFieldDelegate {

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case infinitiveTextField: infinitiveReturnSubject.send(())
        case presentTextField: presentReturnSubject.send(())
        case pastTextField: pastReturnSubject.send(())
        default: ()
        }

        return true
    }

}
