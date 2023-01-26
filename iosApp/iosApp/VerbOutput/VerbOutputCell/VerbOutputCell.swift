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
