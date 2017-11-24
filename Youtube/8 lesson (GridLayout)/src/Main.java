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


        JPanel panel = new JPanel(new GridLayout(3,3));
        JPanel panel2 = new JPanel(new BorderLayout());
        JButton button1 = new JButton(" Button 1");
        JButton button2 = new JButton(" Button 2");
        JButton button3 = new JButton(" Button 3");
        JButton button4 = new JButton(" Button 4");
        JButton button5 = new JButton(" Button 5");
        JButton button6 = new JButton(" Button 6");
        JButton button7 = new JButton(" Button 7");
        JButton button8 = new JButton(" Button 8");
        JButton button9 = new JButton(" Button 9");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);

        JTextField textField = new JTextField("  ");
        panel2.add(textField,BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}
