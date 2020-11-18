import UIKit
import Firebase

final class FavoriteViewController: UIViewController {
    
    private let favoriteViewModel = FavoriteViewModel()
    
    let imageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.image = UIImage(named: "not_found")
        return imageView
    }()
    
    let nameLabel: UILabel = {
        let name = UILabel()
        name.text = "No favorite character!"
        name.textAlignment = NSTextAlignment.center
        name.font = UIFont(name: name.font.fontName, size: 20)
        return name
    }()
    
    let descriptionLabel: UILabel = {
        let label = UILabel()
        label.text = "Please, choose your favorite character"
        label.textAlignment = NSTextAlignment.center
        label.numberOfLines = 0
        return label
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Sign out", style: .plain, target: self, action: #selector(signOut))
        setupNavBar()
        setupView()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        favoriteViewModel.updateFavoriteHero { favoriteHero in
            guard let favoriteHero = favoriteHero,
                let name = favoriteHero.name,
                let description = favoriteHero.description,
                let image = favoriteHero.image else {
                    DispatchQueue.main.async {
                        self.nameLabel.text = "No favorite character!"
                        self.descriptionLabel.text = "Please, choose your favorite character"
                        self.imageView.image = UIImage(named: "not_found")
                    }
                    return
            }
            DispatchQueue.main.async {
                self.nameLabel.text = name
                self.descriptionLabel.text = description
                self.imageView.image = image
            }
        }
    }
    
    func setupNavBar() {
        navigationItem.title = "Favorite Character"
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.boldSystemFont(ofSize: 20)]
    }
    
    func setupView() {
        view.backgroundColor = UIColor.white
        view.addSubview(imageView)
        view.addSubview(nameLabel)
        view.addSubview(descriptionLabel)
        
        imageView.setAnchor(top: view.topAnchor,
                            left: view.leftAnchor,
                            bottom: nil,
                            right: view.rightAnchor,
                            paddingTop: 100,
                            paddingLeft: 0,
                            paddingBottom: 0,
                            paddingRight: 0,
                            width: view.frame.width,
                            height: view.frame.width)
        
        nameLabel.setAnchor(top: imageView.bottomAnchor,
                            left: imageView.leftAnchor,
                            bottom: nil,
                            right: view.rightAnchor,
                            paddingTop: 0,
                            paddingLeft: 0,
                            paddingBottom: 0,
                            paddingRight: 0,
                            width: 0,
                            height: 20)
        
        descriptionLabel.setAnchor(top: nameLabel.bottomAnchor,
                                   left: nameLabel.leftAnchor,
                                   bottom: nil,
                                   right: nameLabel.rightAnchor,
                                   paddingTop: 50,
                                   paddingLeft: 0,
                                   paddingBottom: 5,
                                   paddingRight: 0)
        
        imageView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        nameLabel.centerXAnchor.constraint(equalTo: imageView.centerXAnchor).isActive = true
    }
    
    @objc func signOut() {
        do {
            try favoriteViewModel.signOut()
            let newViewController = AuthorizationViewController()
            self.present(newViewController, animated: true, completion: nil)
        } catch {
            print("Sign out error")
        }
    }
}
