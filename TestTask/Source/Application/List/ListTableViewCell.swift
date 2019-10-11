import UIKit

final class ListTableViewCell: UITableViewCell {
    let cellView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.setCellShadow()
        return view
    }()
    
    let imgView: UIImageView = {
        let imgView = UIImageView()
        imgView.contentMode = .scaleAspectFit
        imgView.image = UIImage(named: "not_found")
        return imgView
    }()
    
    let nameLabel: UILabel = {
        let name = UILabel()
        name.text = "Name"
        name.textAlignment = NSTextAlignment.center
        name.font = UIFont(name: name.font.fontName, size: 20)
        return name
    }()
    
    let descriptionLabel: UILabel = {
        let label = UILabel()
        label.text = "Description"
        label.numberOfLines = 0
        return label
    }()
    
    var task: URLSessionTask?
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setup() {
        addSubview(cellView)
        addSubview(imgView)
        addSubview(nameLabel)
        addSubview(descriptionLabel)
        cellView.setAnchor(top: topAnchor,
                           left: leftAnchor,
                           bottom: bottomAnchor,
                           right: rightAnchor,
                           paddingTop: 4,
                           paddingLeft: 8,
                           paddingBottom: 4,
                           paddingRight: 8)
        
        imgView.setAnchor(top: nil,
                          left: cellView.leftAnchor,
                          bottom: nil,
                          right: nil,
                          paddingTop: 0,
                          paddingLeft: 8,
                          paddingBottom: 0,
                          paddingRight: 0,
                          width: 150,
                          height: 150)
        
        nameLabel.setAnchor(top: imgView.topAnchor,
                            left: imgView.rightAnchor,
                            bottom: nil,
                            right: rightAnchor,
                            paddingTop: 5,
                            paddingLeft: 20,
                            paddingBottom: 0,
                            paddingRight: 20,
                            width: 0,
                            height: 20)
        
        descriptionLabel.setAnchor(top: nameLabel.bottomAnchor,
                                   left: imgView.rightAnchor,
                                   bottom: imgView.bottomAnchor,
                                   right: rightAnchor,
                                   paddingTop: 0,
                                   paddingLeft: 20,
                                   paddingBottom: 5,
                                   paddingRight: 20)
        
        imgView.centerYAnchor.constraint(equalTo: cellView.centerYAnchor).isActive = true
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        task?.cancel()
    }
}
