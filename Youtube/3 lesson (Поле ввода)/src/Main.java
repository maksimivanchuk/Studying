import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args)  throws InterruptedException {
        // write your code here
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение
        frame.setLayout(new GridBagLayout());

        JTextField textField = new JTextField(20);
        textField.setText(" Something >> ");
        textField.setEditable(false);// запрет на ввод
        frame.add(textField);

        JPasswordField passwordField = new JPasswordField(22);// ввод строки для пароля
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.RED);
        frame.add(passwordField);

        frame.setVisible(true);//видимость
    }
}
