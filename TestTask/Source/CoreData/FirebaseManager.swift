import Firebase

final class FirebaseManager {
    
    var ref: DatabaseReference!
    
    init() {}
    
    func createCharacter(id: Int) {
        guard let userID = UserDefaults.standard.string(forKey: "userID") else { return }
        ref = Database.database().reference()
        ref.child("favoriteHero").child(userID).setValue(["id": id])
    }
    
    func deleteCharacter() {
        guard let userID = UserDefaults.standard.string(forKey: "userID") else { return }
        ref = Database.database().reference()
        ref.child("favoriteHero").child(userID).setValue(nil)
    }
    
    func getUserID() throws -> String {
        guard let userID = UserDefaults.standard.string(forKey: "userID") else { throw "Error getUserID" }
        return userID
    }
    
    func getUserCharacterID(completion: @escaping (String?) -> Void) {
        ref = Database.database().reference()
        ref.observe(.value, with: { snapshot in
            if !snapshot.exists() {
                completion(nil)
                return
            }
            var userID: String?
            do {
                userID = try self.getUserID()
            } catch { }
            guard let id = userID else {
                completion(nil)
                return
            }
            let value = snapshot.value as? NSDictionary
            let valueDictionary = value?["favoriteHero"] as? NSDictionary
            let valueID = valueDictionary?[id] as? NSDictionary
            if let id = valueID?["id"] as? Int64 {
                completion(String(describing: id))
            } else {
                completion(nil)
            }
        })
    }
}
