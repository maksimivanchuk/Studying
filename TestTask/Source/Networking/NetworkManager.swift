import UIKit

final class NetworkManager {
    
    enum Config {
        static let baseURL = "https://gateway.marvel.com/v1/public/characters"
        static let limit = "?limit=10"
        static let offset = "&offset="
        static let publicKey = "37aa3f6f0c64f0b47c5ad4f653c7c1f3"
        static let privateKey = "cdfe91b4d73384689c962082994858e768033dbe"
       // static let publicKey = "6930165e0658eb15f06c5a407b1fe571"
        //static let privateKey = "59243ee1a1a8c3bcc077df1a463024696c8133b8"
    }
    
    func loadData(offset: Int, completion: @escaping ([String: Any]?) -> Void) {
        let timestamp = Date().timeIntervalSince1970
        let ts = String(Int(timestamp * 1000))
        let hash = (ts + Config.privateKey + Config.publicKey).utf8.md5
        let jsonUrlString = Config.baseURL + Config.limit + Config.offset + String(offset) + "&ts=" + ts + "&apikey=" + Config.publicKey + "&hash=" + String(hash)
        guard let url = URL(string: jsonUrlString) else { return }
        let task = URLSession.shared.dataTask(with: url) { data, response, err in
            guard let data = data else { return }
            do {
                let jsonResult = try JSONSerialization.jsonObject(with: data, options: []) as! [String: Any]
                completion(jsonResult)
            } catch let jsonErr {
                print("Error serializing json:", jsonErr)
                completion(nil)
            }
        }
        task.resume()
    }
    
    func loadComics(characterId: String, completion: @escaping ([String: Any]?) -> Void) {
        let timestamp = Date().timeIntervalSince1970
        let ts = String(Int(timestamp * 1000))
        let hash = (ts + Config.privateKey + Config.publicKey).utf8.md5
        let jsonUrlString = Config.baseURL + "/" + characterId + "/comics?" + "&ts=" + ts + "&apikey=" + Config.publicKey + "&hash=" + String(hash)
        guard let url = URL(string: jsonUrlString) else { return }
        let task = URLSession.shared.dataTask(with: url) { data, response, err in
            guard let data = data else { return }
            do {
                let jsonResult = try JSONSerialization.jsonObject(with: data, options: []) as! [String: Any]
                completion(jsonResult)
            } catch let jsonErr {
                print("Error serializing json:", jsonErr)
                completion(nil)
            }
        }
        task.resume()
    }
    
    func loadCharacter(characterId: String, completion: @escaping ([String: Any]?) -> Void) {
        let timestamp = Date().timeIntervalSince1970
        let ts = String(Int(timestamp * 1000))
        let hash = (ts + Config.privateKey + Config.publicKey).utf8.md5
        let jsonUrlString = Config.baseURL + "/" + characterId + "?" + "&ts=" + ts + "&apikey=" + Config.publicKey + "&hash=" + String(hash)
        guard let url = URL(string: jsonUrlString) else { return }
        let task = URLSession.shared.dataTask(with: url) { data, response, err in
            guard let data = data else { return }
            do {
                let jsonResult = try JSONSerialization.jsonObject(with: data, options: []) as! [String: Any]
                completion(jsonResult)
            } catch let jsonErr {
                print("Error serializing json:", jsonErr)
                completion(nil)
            }
        }
        task.resume()
    }
    
    func loadImage(imgURL: URL, completion: @escaping (UIImage) -> Void) {
        var comps = URLComponents(url: imgURL, resolvingAgainstBaseURL: true)
        comps?.scheme = "https"
        guard let compsString = comps?.string,
            let url = URL(string: compsString) else { return }
        DispatchQueue.global().async {
            guard let data = try? Data(contentsOf: url) else {
                print("loadImage error: from URL to Data")
                guard let image = UIImage(named: "not_found") else { return }
                completion(image)
                return
            }
            guard let image = UIImage(data: data) else {
                print ("loadImage error: from Data to UIImage")
                return
            }
            completion(image)
        }
    }
}
