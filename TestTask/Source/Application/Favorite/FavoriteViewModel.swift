import Firebase

final class FavoriteViewModel {
    
    private let favoriteModel = FavoriteModel()
    
    private let firebaseManager = FirebaseManager()
    
    private let network = NetworkManager()
    
    func updateFavoriteHero(completion: @escaping (Character?) -> Void) {
        firebaseManager.getUserCharacterID { characterIDFromFirebase in
            //Hierarchy: Firebase -> CoreData. Firebase has the highest priority
            guard let characterIDFromFirebase = characterIDFromFirebase else {
                self.favoriteModel.favoriteHero = nil
                completion(self.favoriteModel.favoriteHero)
                return
            }
            guard let characterFromCoreData = self.favoriteModel.manager.getCharacter() else {
                self.network.loadCharacter(characterId: characterIDFromFirebase) { result in
                    guard let result = result else {
                        print("loadData error: result is empty")
                        return
                    }
                    self.didReceiveData(searchResult: result, completion: completion)
                }
                return
            }
            if let characterIDFromCoreData = characterFromCoreData.id,
                characterIDFromFirebase != String(characterIDFromCoreData) {
                self.network.loadCharacter(characterId: characterIDFromFirebase) { result in
                    guard let result = result else {
                        print("loadData error: result is empty")
                        return
                    }
                    self.didReceiveData(searchResult: result, completion: completion)
                }
            } else {
                if self.favoriteModel.favoriteHero?.id != characterFromCoreData.id {
                    self.favoriteModel.favoriteHero = characterFromCoreData
                }
                completion(self.favoriteModel.favoriteHero)
            }
        }
    }
    
    func didReceiveData(searchResult: [String: Any], completion: @escaping (Character) -> Void) {
        guard let oldresults = searchResult["data"] as? [String: Any],
            let results = oldresults["results"] as? [[String: Any]] else {
                print("Could not process search results...")
                return
        }
        for result in results {
            guard let id = result["id"] as? Int,
                let name = result["name"] as? String,
                let description = result["description"] as? String,
                let thumbnail = result["thumbnail"] as? [String: String],
                let path = thumbnail["path"],
                let extensions = thumbnail["extension"],
                let imageURL = path + "." + extensions as String? else {
                    print("Error parsing favoriteHero")
                    break
            }
            guard let url = URL(string: imageURL) else {
                print("didReceiveData error: from String to URL")
                return
            }
            network.loadImage(imgURL: url) { img in
                self.favoriteModel.favoriteHero = Character(id: id, name: name, description: description, image: img)
                guard let favoriteHero = self.favoriteModel.favoriteHero else { return }
                completion(favoriteHero)
            }
        }
    }
    
    func signOut() throws {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            favoriteModel.manager.deleteCharacter(isComingOut: true)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
            throw "Error signing out"
        }
    }
}
