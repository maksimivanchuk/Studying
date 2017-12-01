import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    private String title;
    private Dimension d;
    private int progress = 0;

    private JTextField text1Field = new JTextField("0", 10);
    private JTextField text2Field = new JTextField("0", 10);
    private JTextField text3Field = new JTextField("0", 10);
    private JTextField textField = new JTextField("", 10);

    private JButton button1 = new JButton(" Рассчитать");
    private JButton button2 = new JButton(" Вывести в файл");
    private JButton button3 = new JButton(" Сумма");

    private JLabel label2 = new JLabel(" ");

    private JLabel A0 = new JLabel("A0");
    private JLabel q = new JLabel("Разность прогрессии ");
    private JLabel n = new JLabel("Количество ");

    private JRadioButton Liner = new JRadioButton(" Арифметическая прогрессия");
    private JRadioButton Exponential = new JRadioButton(" Геометрическая прогрессия ");

    public MainFrame(String title, Dimension d) {
        this.title = title;
        this.d = d;
    }

    public void init() {
        setTitle(title);
        setSize(d);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ButtonGroup group = new ButtonGroup();
        group.add(Liner);
        group.add(Exponential);

        button1.addActionListener(new ButtonActionListener1());
        button2.addActionListener(new ButtonActionListener2());
        button3.addActionListener(new ButtonActionListener3());

        Liner.addActionListener(new RadioButtonActionListener1());
        Exponential.addActionListener(new RadioButtonActionListener2());

        add(Liner, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        add(Exponential, new GridBagConstraints(2, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        add(text1Field, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(text2Field, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(text3Field, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(button1, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(button2, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

        add(textField, new GridBagConstraints(0, 4, 3, 2, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
        add(button3, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(label2, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
        add(A0, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        add(q, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        add(n, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));

        setVisible(true);
        pack();
    }

    public class ButtonActionListener1 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str1 = text1Field.getText();
            String str2 = text2Field.getText();
            String str3 = text3Field.getText();

            if (str1.length() == 0 || str2.length() == 0 || str3.length() == 0) {
                str1 = "0";
                str2 = "0";
                str3 = "0";
                text1Field.setText("0");
                text2Field.setText("0");
                text3Field.setText("0");
            }

            if (progress >= 1) {
                if (progress == 1) {

                    Liner a = new Liner(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    String str = a.toString();
                    textField.setText(str);
                } else {
                    Exponential a = new Exponential(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    String str = a.toString();
                    textField.setText(str);
                }


            }
        }

        ;
    }

    public class ButtonActionListener3 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str1 = text1Field.getText();
            String str2 = text2Field.getText();
            String str3 = text3Field.getText();

            if (str1.length() == 0 || str2.length() == 0 || str3.length() == 0) {
                str1 = "0";
                str2 = "0";
                str3 = "0";
                text1Field.setText("0");
                text2Field.setText("0");
                text3Field.setText("0");
            }

            if (progress >= 1) {
                if (progress == 1) {
                    Liner a = new Liner(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    label2.setText(String.valueOf(a.getSum()));
                } else {
                    Exponential a = new Exponential(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    label2.setText(String.valueOf(a.getSum()));
                }


            }
        }

        ;
    }

    public class ButtonActionListener2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String str1 = text1Field.getText();
            String str2 = text2Field.getText();
            String str3 = text3Field.getText();

            if (str1.length() == 0 || str2.length() == 0 || str3.length() == 0) {
                str1 = "0";
                str2 = "0";
                str3 = "0";
                text1Field.setText("0");
                text2Field.setText("0");
                text3Field.setText("0");
            }
            if (progress >= 1) {
                if (progress == 1) {
                    Liner a = new Liner(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    a.FileRecoder(a.toString());
                } else {
                    Exponential a = new Exponential(Double.valueOf(str1), Double.valueOf(str2), Integer.valueOf(str3));
                    a.FileRecoder(a.toString());
                }

            }

        }

        ;
    }

    public class RadioButtonActionListener1 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            progress = 1;
        }

        ;
    }

    public class RadioButtonActionListener2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            progress = 2;
        }

        ;
    }
}

