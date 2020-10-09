import UIKit

final class ListModel {
    
    let manager = Manager(persistenceManager: PersistenceManager.shared)
    var pred: Int?
    var offset = 0
    var items = [Character]()
}
