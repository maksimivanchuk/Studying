import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600,400 );
        frame.setTitle("This is my first title form"); // тоже самое что и передача строки в конструктор
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//действие на крестик
        frame.setLocationRelativeTo(null);// размещение

        frame.setVisible(true);//видимость

        Thread.sleep(2000);
        frame.setState(JFrame.ICONIFIED); // свернуть
        Thread.sleep(2000);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//развернуть на весь экран
        Thread.sleep(2000); /// Timer
        frame.setExtendedState(JFrame.NORMAL);//развернуть на нормалбное т е указанное в setSize
    }
}
