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
// Created by Maksim Sviatlou on 18.01.23.

    
import UIKit
import LumiereToolkit
import shared
import Combine

private let backImage = UIImage(systemName: "arrow.backward")?.withRenderingMode(.alwaysTemplate)

final class VerbOutputViewController: ReactiveViewController,
                                      UITableViewDelegate,
                                      UITableViewDataSource,
                                      UIGestureRecognizerDelegate {
    @IBOutlet weak var tableView: UITableView!
    private var items: [VerbOutput.Item] = []

    private static let estimatedRowHeight = VerbOutputCell.instantiateFromNib().frame.height

    let onBack: AnyPublisher<Void, Never>
    private let backSubject = PassthroughSubject<Void, Never>()

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        onBack = backSubject.eraseToAnyPublisher()
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }

    required init?(coder: NSCoder) { fatalError("init(coder:) has not been implemented") }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.contentInset = additionalSafeAreaInsets
        tableView.estimatedRowHeight = Self.estimatedRowHeight
        tableView.rowHeight = UITableView.automaticDimension
        tableView.register(VerbOutputCell.self)

        navigationItem.setLeftBarButton(.init(image: backImage, style: .done, target: self, action: #selector(back)),
                                        animated: false)
        navigationItem.leftBarButtonItem?.tintColor = .systemMint
    }

    func set(items: [VerbOutput.Item]) {
        self.items = items
        tableView.reloadData()
    }

    // MARK: - UITableViewDataSource

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        items.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(VerbOutputCell.self, for: indexPath)

        cell.set(tense: items[indexPath.row].tense)
        cell.set(forms: items[indexPath.row].forms)
        return cell
    }

    // MARK: - actions

    @objc func back() {
        backSubject.send(())
    }

}
