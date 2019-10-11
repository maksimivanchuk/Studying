import UIKit
import Firebase

final class ListViewModel {
    let cellID = "cellID"
    var fetchingMore = false
    let listModel = ListModel()
    let network = NetworkManager()
    
    func didReceiveData(searchResult: [String: Any]) {
        guard let oldresults = searchResult["data"] as? [String: Any],
            let results = oldresults["results"] as? [[String: Any]] else {
                print("Could not process search results...")
                return
        }
        
        var apps = [[String: String]]()
        for result in results {
            guard let id = result["id"] as? Int,
                let name = result["name"] as? String,
                let description = result["description"] as? String,
                let thumbnail = result["thumbnail"] as? [String: String],
                let path = thumbnail["path"],
                let extensions = thumbnail["extension"],
                let imageURL = path + "." + extensions as String? else {
                    break
            }
            apps.append(["id": String(id),
                         "name": name,
                         "description": description,
                         "imageURL": imageURL])
        }
        for character in apps {
            listModel.items.append(Character(id: Int(character["id"]!)!,
                                                  name: character["name"]!,
                                                  description: character["description"]!,
                                                  imageURL: character["imageURL"]!))
        }
        self.listModel.offset += 10
        print(listModel.items.count)
    }
    
    func loadData(offset: Int, completion: @escaping () -> Void) {
        network.loadData(offset: offset) { result in
            guard let result = result else {
                print("loadData error: result is empty")
                return
            }
            self.didReceiveData(searchResult: result)
            self.fetchingMore = false
            completion()
        }
    }
    
    //begin paging
    func beginBatchFetch(completion: @escaping () -> Void) {
        self.fetchingMore = true
        loadData(offset: listModel.offset) {
            completion()
        }
    }
    
    func swipeAction(indexPath: IndexPath) -> UISwipeActionsConfiguration {
        guard listModel.items[indexPath.row].isLoaded else {
            return UISwipeActionsConfiguration()
        }
        let favorite = favoriteAction(at: indexPath)
        if listModel.items[indexPath.row].isFavorite {
            listModel.manager.deleteCharacter(isComingOut: false)
        } else {
            guard let image = listModel.items[indexPath.row].image,
                let imageData = image.pngData(),
                let id = listModel.items[indexPath.row].id,
                let name = listModel.items[indexPath.row].name,
                let info = listModel.items[indexPath.row].description else { return UISwipeActionsConfiguration() }
            listModel.manager.createCharacter(id: id,
                                              name: name,
                                              info: info,
                                              img: imageData)
        }
        return UISwipeActionsConfiguration(actions: [favorite])
    }
    //action for swiping
    func favoriteAction(at indexPath: IndexPath) -> UIContextualAction {
        if let pred = listModel.pred,
            pred != indexPath.row,
            listModel.items[pred].isFavorite {
            listModel.items[pred].isFavorite = !listModel.items[pred].isFavorite
            listModel.manager.deleteCharacter(isComingOut: false)
        }
        listModel.pred = indexPath.row
        
        let item = listModel.items[indexPath.row]
        let title = item.isFavorite ? "Unfavorite" : "Favorite"
        let action = UIContextualAction(style: .normal, title: title) { action, view, completion in
            self.listModel.items[indexPath.row].isFavorite = !self.listModel.items[indexPath.row].isFavorite
            completion(true)
        }
        action.image = item.isFavorite ? UIImage(named: "unfavorite") : UIImage(named: "favorite")
        action.backgroundColor = item.isFavorite ? .black : .red
        return action
    }
    
    func updateCell(cell: ListTableViewCell, indexPath: IndexPath) -> ListTableViewCell {
        cell.nameLabel.text = self.listModel.items[indexPath.row].name
        cell.descriptionLabel.text = self.listModel.items[indexPath.row].description
        cell.imgView.image = self.listModel.items[indexPath.row].image
        
        guard let imgURLString = self.listModel.items[indexPath.row].imageURL else {
                return cell
        }
        if !self.listModel.items[indexPath.row].isLoaded {
            cell.imgView.downloadImageFrom(link: imgURLString, contentMode: .scaleToFill) {
                self.listModel.items[indexPath.row].isLoaded = true
                self.listModel.items[indexPath.row].image = cell.imgView.image
            }
        }
        return cell
    }
    
    func signOut() throws {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            listModel.manager.deleteCharacter(isComingOut: true)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
            throw "Error signing out"
        }
    }
}
