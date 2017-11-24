 import javax.swing.*;
 import java.awt.*;

 public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение
        frame.setLayout(new GridBagLayout());

        JButton button1 = new JButton();
        button1.setText(" first button");// тоже самое что и передача строки в конструктор
        frame.add(button1);
        JButton button2 = new JButton(" second button");
        frame.add(button2);

        button1.setBackground(new Color(188,23,255));// цвет сзади
        button1.setForeground(new Color(255,23,12));//цвет текста на кнопке

        button1.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));

        frame.setVisible(true);//видимость
    }

}
