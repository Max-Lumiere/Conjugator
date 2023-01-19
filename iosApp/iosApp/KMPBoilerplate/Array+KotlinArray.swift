//
//  Created by Maksim Sviatlou on 18.01.23.
    

import shared

extension Array where Element: AnyObject {

    init(_ kotlinArray: KotlinArray<Element>) {
        self.init()

        let iterator = kotlinArray.iterator()

        while iterator.hasNext() {
            append(iterator.next() as! Element)
        }
    }
}
