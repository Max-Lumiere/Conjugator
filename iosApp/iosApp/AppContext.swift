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

import shared

final class AppContext: Context {
    private let stringsProvider = StringsProvider()
    private let config = AppConfiguration()

    let analytics: Analytics

    override init() {
        if config.analyticsIsEnabled {
            analytics = FirebaseAnalytics(string: stringsProvider.getRedString())
        } else {
            analytics = AnalyticsStub()
        }
        super.init()
    }

}
