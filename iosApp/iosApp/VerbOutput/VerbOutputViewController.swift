//
//  Created by Maksim Sviatlou on 18.01.23.
    
import UIKit
import LumiereToolkit
import shared

final class VerbOutputViewController: ReactiveViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var tableView: UITableView!
    private var conjunctions: [VerbConjunction] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.contentInset = additionalSafeAreaInsets
        tableView.estimatedRowHeight = VerbOutputCell.instantiateFromNib().frame.height
        tableView.rowHeight = UITableView.automaticDimension
        tableView.register(VerbOutputCell.self)
    }

    func set(conjunctions: [VerbConjunction]) {
        self.conjunctions = conjunctions
        tableView.reloadData()
    }

    // MARK: - UITableViewDataSource

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        conjunctions.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(VerbOutputCell.self, for: indexPath)

        cell.set(tense: "Present")
        cell.set(forms: conjunctions[indexPath.row].forms)
        return cell
    }
}
