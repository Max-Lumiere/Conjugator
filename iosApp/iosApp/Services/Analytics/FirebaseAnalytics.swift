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
import Firebase

final class FirebaseAnalytics: Analytics {

    init(string: String) {
        guard FirebaseApp.app() == nil else { return }
        if let options = FirebaseOptions.defaultOptions() {

            options.apiKey = string
            FirebaseApp.configure(options: options)
        } else {
            FirebaseApp.configure()
        }
    }

    func track(_ event: Event) {
        Firebase.Analytics.logEvent(event.name, parameters: event.info)
    }

    func setUserProperty(value: String?, name: String) {
        Firebase.Analytics.setUserProperty(value, forName: name)
    }
}
