import UIKit
import Firebase

final class ListViewController: UIViewController {
    
    private let listViewModel = ListViewModel()

    private let tableView: UITableView = {
        let tableView = UITableView()
        tableView.separatorStyle = .none
        tableView.allowsSelection = true
        return tableView
    }()
    
    private var observer: AnyObject?
    
    @objc func refreshData() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if UserDefaults.standard.string(forKey: "userID") == nil {
            signIn()
        }
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Sign out", style: .plain, target: self, action: #selector(signOut))
        setupNavBar()
        setupTableView()
        listViewModel.loadData(offset: 0) { [weak self] in
            self?.refreshData()
        }
    }
    
    func setupNavBar() {
        navigationItem.title = "Characters List"
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black, NSAttributedString.Key.font: UIFont.boldSystemFont(ofSize: 20)]
    }
    
    func setupTableView(){
        view.backgroundColor = .gray
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(ListTableViewCell.self, forCellReuseIdentifier: self.listViewModel.cellID)
        view.addSubview(tableView)
        tableView.setAnchor(top: view.topAnchor,
                            left: view.leftAnchor,
                            bottom: view.bottomAnchor,
                            right: view.rightAnchor,
                            paddingTop: 0,
                            paddingLeft: 0,
                            paddingBottom: 0,
                            paddingRight: 0)
    }
    
    @objc func signIn() {
        let newViewController = AuthorizationViewController()
        self.present(newViewController, animated: false, completion: nil)
    }
    
    @objc func signOut() {
        do {
            try listViewModel.signOut()
            let newViewController = AuthorizationViewController()
            self.present(newViewController, animated: true, completion: nil)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }
}

extension ListViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listViewModel.listModel.items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: self.listViewModel.cellID, for: indexPath) as! ListTableViewCell
        return listViewModel.updateCell(cell: cell, indexPath: indexPath)
        
    }
    
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        return listViewModel.swipeAction(indexPath: indexPath)
    }
}

extension ListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 160
    }

    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height
        if offsetY > contentHeight - scrollView.frame.height * 2 {
            if !listViewModel.fetchingMore {
                listViewModel.beginBatchFetch { [weak self] in
                    self?.refreshData()
                }
            }
        }
    }
}
