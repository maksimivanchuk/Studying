import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    private String title;
    private Dimension d;
    private int ammount=0;

    private JTextField text1Field = new JTextField(60);
    private JTextField text2Field = new JTextField(60);

    private JButton button1 = new JButton(" button1");
    private JButton button2= new JButton(" button2");
    private JLabel label = new JLabel("lab");


    public MainFrame (String title, Dimension d){
        this.title=title;
        this.d=d;
    }

    public void init(){
        setTitle(title);
        setSize(d);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        button1.addActionListener(new ButtonActionListener());
        add( text1Field, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(1,1,1,1),0,0));
        add( text2Field, new GridBagConstraints(1,0,1,1,1,1, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(1,1,1,1),0,0));
        add( button1, new GridBagConstraints(0,1,2,1,1,1, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(1,1,1,1),0,0));
        add( label, new GridBagConstraints(0,2,1,1,1,1, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(1,1,1,1),0,0));

        setVisible(true);
       // pack();
    }

    public  class ButtonActionListener implements ActionListener{

        public void actionPerformed (ActionEvent e){
        String str1= text1Field.getText();
        String str2=text2Field.getText();

        text1Field.setText(str2);
        text2Field.setText(str1);
        ammount++;
        label.setText(String.valueOf(ammount));

        };
    }
}

