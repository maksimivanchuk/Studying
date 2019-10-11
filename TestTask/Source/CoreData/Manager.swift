import UIKit
import Firebase

final class Manager {
    let persistenceManager: PersistenceManager
    
    let firebaseManager = FirebaseManager()
    
    init(persistenceManager: PersistenceManager) {
        self.persistenceManager = persistenceManager
        self.deleteCharacter(isComingOut: true)
    }
    
    func createCharacter(id: Int, name: String, info: String, img: Data) {
        let characters = persistenceManager.fetch(Hero.self)
        if (characters.count > 0) {
            let character = characters[0]
            character.id = Int64(id)
            character.name = name
            character.img = img
            character.info = info
        } else {
            let character = Hero(context: persistenceManager.context)
            character.id = Int64(id)
            character.name = name
            character.img = img
            character.info = info
        }
        firebaseManager.createCharacter(id: id)
        persistenceManager.save()
        print("created")
    }
    
    func deleteCharacter(isComingOut: Bool) {
        let characters = persistenceManager.fetch(Hero.self)
        for character in characters {
            persistenceManager.context.delete(character)
        }
        if !isComingOut {
            firebaseManager.deleteCharacter()
        }
        persistenceManager.save()
        print("deleted")
    }
    
    func getCharacter() -> Character? {
        print("get")
        let characters = persistenceManager.fetch(Hero.self)
        print(characters.count)
        guard characters.count != 0,
            let name = characters[0].name,
            let description = characters[0].info,
            let imageData = characters[0].img,
            let image = UIImage(data: imageData) else {
                print ("EMPTY")
                return nil
        }
        let id = characters[0].id
        print(id)
        return Character(id: Int(id), name: name, description: description, image: image)
    }
}
