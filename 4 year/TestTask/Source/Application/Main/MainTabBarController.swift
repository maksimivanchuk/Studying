import UIKit
import CoreData

final class MainTabBarController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setupTabBar()
    }
    
    func setupTabBar() {
        let characterListController = UINavigationController(rootViewController: ListViewController())
        characterListController.tabBarItem.image = UIImage(named: "list")
        characterListController.tabBarItem.title = "List"
        
        let favoriteController = UINavigationController(rootViewController: FavoriteViewController())
        favoriteController.tabBarItem.title = "Favorite"
        favoriteController.tabBarItem.image = UIImage(named: "favorite")
        
        viewControllers = [characterListController, favoriteController]
    }
}

