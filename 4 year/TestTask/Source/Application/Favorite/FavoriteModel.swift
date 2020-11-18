import Foundation

final class FavoriteModel {
    
    let manager = Manager(persistenceManager: PersistenceManager.shared)
    
    var favoriteHero: Character? {
        didSet {
            guard let favoriteHero = favoriteHero,
                let id = favoriteHero.id,
                let name = favoriteHero.name,
                let info = favoriteHero.description,
                let image = favoriteHero.image,
                let imageData = image.pngData(),
                manager.getCharacter() != nil
                else { return }
            manager.createCharacter(id: id, name: name, info: info, img: imageData)
            print("didSet")
        }
    }
}
