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
// Created by Maksim Sviatlou on 29.01.23.
    

import Foundation
import shared

final class StringsProvider: shared.StringsProvider {

    func getRedString() -> String {
        let string1 = NSLocalizedString("polished_red_string_1", tableName: "Strings" , comment: "")
        let string2 = NSLocalizedString("polished_red_string_2", tableName: "Strings" , comment: "")

        return getStringFrom(string: string1) + getStringFrom(string: string2)
    }

}
