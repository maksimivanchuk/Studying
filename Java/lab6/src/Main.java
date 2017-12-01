import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static int xPosition;
    private static int yPosition;

    public static void main(String[] args) {
        JFrame frame = new JFrame(" This is my first title form ");

        frame.setSize(600, 400);
        frame.setTitle("This is my first title form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setFocusable(true);

        JLabel label = new JLabel(" Label ");
        label.setBounds(0, 0, 100, 20);

        JButton button = new JButton(" Button ");
        button.setBounds(0, 0, 100, 30);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        panel.setLayout(null);
        panel.add(button);

        panel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) { }

            @Override
            public void mouseMoved(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                label.setText(" x = " + xPosition + "; y = " + yPosition);
            }
        });

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!e.isControlDown()) {
                    xPosition = e.getX();
                    yPosition = e.getY();
                    button.setBounds(e.getX()-50, e.getY()-15, 100, 30);
                }
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown())
                {
                    int dx = xPosition-(button.getX());
                    int dy =yPosition-(button.getY());

                    xPosition = button.getX() + e.getX();
                    yPosition = button.getY() + e.getY();

                    button.setBounds(xPosition-dx,  yPosition-dy, 100, 30);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                xPosition = button.getX() + e.getX();
                yPosition = button.getY() + e.getY();

                label.setText(" x = " + xPosition + "; y = " + yPosition);
            }
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String str = button.getText();
                if (c == KeyEvent.VK_BACK_SPACE) {
                    if (str.equals(""))
                        button.setText("");
                    else
                        button.setText(str.substring(0, str.length() - 1));
                }
                else
                    button.setText(str + c);
            }
        });

        frame.setVisible(true);

    }
}
