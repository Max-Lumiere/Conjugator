//
//  Created by Maksim Sviatlou on 18.01.23.
    
import UIKit
import LumiereToolkit
import shared

final class VerbOutputViewController: ReactiveViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var tableView: UITableView!
    private var items: [VerbOutput.Item] = []

    private static let estimatedRowHeight = VerbOutputCell.instantiateFromNib().frame.height

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.contentInset = additionalSafeAreaInsets
        tableView.estimatedRowHeight = Self.estimatedRowHeight
        tableView.rowHeight = UITableView.automaticDimension
        tableView.register(VerbOutputCell.self)
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
}