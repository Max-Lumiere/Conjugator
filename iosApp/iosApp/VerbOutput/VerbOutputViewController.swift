//
//  Created by Maksim Sviatlou on 18.01.23.
    
import UIKit
import LumiereToolkit

final class VerbOutputViewController: ReactiveViewController {

    @IBOutlet weak var tableView: UITableView!

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.contentInset = additionalSafeAreaInsets
    }

}
