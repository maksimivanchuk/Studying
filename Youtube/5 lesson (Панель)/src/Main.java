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
        frame.setLayout(new GridBagLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        JButton button = new JButton(" The button ");

        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.RED);


        panel1.add(button);
        panel1.add(panel2);


        frame.add(panel1);

        Component[] components = panel1.getComponents();

        for (int i = 0; i < components.length; i++){
            if (components[i] instanceof JPanel)
                System.out.println("panel");
            if (components[i] instanceof JButton)
                System.out.println("Button");
        }
        frame.setVisible(true);//видимость

    }
}
