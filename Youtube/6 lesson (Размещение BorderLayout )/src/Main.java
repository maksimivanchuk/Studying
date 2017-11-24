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
        frame.setLayout(new BorderLayout());

        JButton button = new JButton( " the button");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.blue);
        panel4.setBackground(Color.black);
        panel5.setBackground(Color.yellow);

        panel1.setLayout(new BorderLayout());
        panel1.add(button,BorderLayout.NORTH);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.PAGE_START);
        frame.add(panel3, BorderLayout.LINE_END);
        frame.add(panel4, BorderLayout.LINE_START);
        frame.add(panel5, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
