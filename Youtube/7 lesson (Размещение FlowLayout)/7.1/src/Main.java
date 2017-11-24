import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение
        frame.setLayout(new BorderLayout());

        JTextField textField = new JTextField(10);
        JButton button1 = new JButton(" the button1");
        JButton button2 = new JButton(" the button2");
        JButton button3 = new JButton(" the button3");
        JButton button4 = new JButton(" the button4");
        JButton button5 = new JButton(" the button5");

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(400,100));

        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(textField);

        frame.add(panel1, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
