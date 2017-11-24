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

        frame.setVisible(true);//видимость

       JLabel label = new JLabel(" Information ");
        Font font = new Font ("Verdana",Font.HANGING_BASELINE,35);// шрифты
        label.setFont(font);
        label.setForeground(new Color(255,12,12));
        frame.add(label);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        //progressBar.setIndeterminate(true);// заполнение

        frame.add(progressBar);

        for (int i=progressBar.getMinimum();i<=progressBar.getMaximum();i++)
        {
            Thread.sleep(40);
            progressBar.setValue(i);
            frame.setVisible(true);
        }


    }
}
