import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение
        frame.setLayout(new FlowLayout());

        JButton [] buttons = new JButton [10];
        JTextField textField = new JTextField(5);

        frame.add(textField);

        for (int i = 0; i< 10 ; i++)
        {
            buttons[i]=new JButton( String.valueOf(i));
            frame.add(buttons[i]);
        }
        frame.setVisible(true);
    }
}
