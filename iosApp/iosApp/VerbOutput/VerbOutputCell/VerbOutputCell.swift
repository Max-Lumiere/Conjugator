//
//  Created by Maksim Sviatlou on 18.01.23.
    

import UIKit
import LumiereToolkit

final class VerbOutputCell: UITableViewCell, NibBased, ReusableView {

    @IBOutlet weak var tenseLabel: UILabel!
    @IBOutlet var formLabels: [UILabel]!

    override func awakeFromNib() {
        super.awakeFromNib()
        clean()
    }

    override func prepareForReuse() {
        super.prepareForReuse()
        clean()
    }

    func set(tense: String?) {
        tenseLabel.text = tense
    }

    func set(forms: [String]) {
        for i in 0..<forms.count {
            formLabels[i].text = forms[i]
        }
    }

    // MARK: - private

    private func clean() {
        tenseLabel.text = nil
        formLabels.forEach { $0.text = nil }
    }
}
