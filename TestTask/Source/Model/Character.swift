import UIKit

struct Character {
    let name: String?
    let description: String?
    var isFavorite: Bool
    let imageURL: String?
    var image: UIImage?
    var isLoaded: Bool
    let id: Int?
    
    init(character: Character) {
        self = character
    }
    
    init(id: Int?, name: String?, description: String?, image: UIImage? = UIImage(named: "not_found"), imageURL: String? = nil) {
        self.id = id
        self.name = name
        self.description = description
        self.imageURL = imageURL
        self.image = image
        self.isFavorite = false
        guard imageURL != nil else {
            self.isLoaded = true
            return
        }
        self.isLoaded = false
    }
}
