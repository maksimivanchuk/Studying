import UIKit

extension UIImageView {
    func downloadImageFrom(link:String, contentMode: UIView.ContentMode, completion: @escaping () -> Void) {
        guard let imgURL = URL(string: link) else {
            print("downloadImageFrom error: url is nil")
            return
        }
        var comps = URLComponents(url: imgURL, resolvingAgainstBaseURL: true)
        comps?.scheme = "https"
        guard let urlStringWithHTTPS = comps?.string,
            let url = URL(string: urlStringWithHTTPS) else {
                print("downloadImageFrom error: url is nil")
                return
        }
        URLSession.shared.dataTask(with: url, completionHandler: { [weak self] data, response, error in
            DispatchQueue.main.async {
                self?.contentMode =  contentMode
                if let data = data {
                    self?.image = UIImage(data: data)
                    completion()
                }
            }
        }).resume()
    }
}
