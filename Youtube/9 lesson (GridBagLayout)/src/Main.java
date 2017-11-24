import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение
        frame.setLayout(new GridBagLayout());

        JLabel LoginLabel = new JLabel("Log");
        JLabel PasswordLabel = new JLabel("Pass");

        JButton Login = new JButton(" Login in ");
        JButton Password = new JButton(" Password in");

        JTextField  loginTextField = new JTextField(15 );
        JPasswordField  passwordField = new JPasswordField(15);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0 ;//установка компонента
        c.gridy =0;

        c.gridwidth =1 ; //количество занятых строк или стобцов
        c.gridheight =1;

        c.weightx= 0.0;//
        c.weighty=0.9;

        c.anchor = GridBagConstraints.SOUTH;
        c.fill =GridBagConstraints.HORIZONTAL ;
        c.insets= null;

       Insets insets = new Insets( 2,23, 300, 133); // отступы
       c.insets = insets;
       c.ipadx =0; // на сколько будет увеличен компонент
       c.ipady =0;

        frame.add (LoginLabel,new GridBagConstraints( 0,0,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.add (loginTextField,new GridBagConstraints( 1,0,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.add (PasswordLabel,new GridBagConstraints( 0,1,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.add (passwordField,new GridBagConstraints( 1,1,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.add (Login,new GridBagConstraints( 0,2,1,1,2,2, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.add (Password,new GridBagConstraints( 1,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        frame.pack();
        frame.setVisible(true);
    }
}
