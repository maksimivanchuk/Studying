import UIKit
import Firebase

final class AuthorizationViewController: UIViewController {
    
    private let greetingLabel: UILabel = {
        let name = UILabel()
        name.text = "Hello!"
        name.textAlignment = NSTextAlignment.center
        name.font = UIFont(name: name.font.fontName, size: 100)
        return name
    }()
    
    private let loginTextField: UITextField = {
        let textInput = UITextField()
        textInput.placeholder = "Login"
        textInput.textAlignment = .center
        textInput.layer.cornerRadius = 10
        textInput.layer.borderWidth = 1
        textInput.autocapitalizationType = .none
        return textInput
    }()
    
    private let passwordTextField: UITextField = {
        let textInput = UITextField()
        textInput.placeholder = "Password"
        textInput.textAlignment = .center
        textInput.layer.cornerRadius = 10
        textInput.layer.borderWidth = 1
        textInput.isSecureTextEntry = true
        textInput.autocapitalizationType = .none
        return textInput
    }()
    
    private let signInButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Sign in", for: .normal)
        button.addTarget(self, action: #selector(signIn), for: .touchUpInside)
        return button
    }()
    
    private let signUpButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("Sign up", for: .normal)
        button.addTarget(self, action: #selector(signUp), for: .touchUpInside)
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
    }
    
    func setupView() {
        view.backgroundColor = UIColor.white
        view.addSubview(greetingLabel)
        view.addSubview(loginTextField)
        view.addSubview(passwordTextField)
        view.addSubview(signInButton)
        view.addSubview(signUpButton)
        
        greetingLabel.setAnchor(top: view.topAnchor,
                                left: view.leftAnchor,
                                bottom: nil,
                                right: view.rightAnchor,
                                paddingTop: 100,
                                paddingLeft: 0,
                                paddingBottom: 0,
                                paddingRight: 0,
                                width: 0,
                                height: 100)
        
        loginTextField.setAnchor(top: greetingLabel.bottomAnchor,
                                 left: greetingLabel.leftAnchor,
                                 bottom: nil,
                                 right: greetingLabel.rightAnchor,
                                 paddingTop: 50,
                                 paddingLeft: view.bounds.size.width / 4,
                                 paddingBottom: 0,
                                 paddingRight: view.bounds.size.width / 4)
        
        passwordTextField.setAnchor(top: loginTextField.bottomAnchor,
                                    left: greetingLabel.leftAnchor,
                                    bottom: nil,
                                    right: greetingLabel.rightAnchor,
                                    paddingTop: 10,
                                    paddingLeft: view.bounds.size.width / 4,
                                    paddingBottom: 0,
                                    paddingRight: view.bounds.size.width / 4)
        
        signInButton.setAnchor(top: passwordTextField.bottomAnchor,
                               left: greetingLabel.leftAnchor,
                               bottom: nil,
                               right: greetingLabel.centerXAnchor,
                               paddingTop: 10,
                               paddingLeft: view.bounds.size.width / 4,
                               paddingBottom: 0,
                               paddingRight: 0)
        
        signUpButton.setAnchor(top: passwordTextField.bottomAnchor,
                               left: signInButton.rightAnchor,
                               bottom: nil,
                               right: greetingLabel.rightAnchor,
                               paddingTop: 10,
                               paddingLeft: 0,
                               paddingBottom: 0,
                               paddingRight: view.bounds.size.width / 4)
        greetingLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        loginTextField.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        passwordTextField.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        signInButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    }
    
    @objc func signUp() {
        guard let email = loginTextField.text,
            let password = passwordTextField.text else {
                return
        }
        Auth.auth().createUser(withEmail: email, password: password) { authResult, error in
            if error != nil {
                self.alert(title: "Error", message: error.debugDescription)
            } else {
                self.alert(title: "Well done", message: "You've been successfully registered")
            }
        }
    }
    @objc func signIn() {
        guard let email = loginTextField.text,
            let password = passwordTextField.text else {
                return
        }
        guard email.isEmpty, password.isEmpty else {
            Auth.auth().signIn(withEmail: email, password: password) { [weak self] user, error in
                guard let self = self else { return }
                if user != nil {
                    guard let userID = Auth.auth().currentUser?.uid else { return }
                    UserDefaults.standard.set(userID, forKey: "userID")
                    self.dismiss(animated: true, completion: nil)
                } else {
                    self.alert(title: "Invalid login or password!", message: "Please, enter the correct login or password")
                }
            }
            return
        }
        alert(title: "Login or password is empty!", message: "Please, enter the correct login or password")
    }
    
    func alert(title: String, message: String) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}
