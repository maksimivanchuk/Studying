import java.awt.*;

class ArgsException extends Exception {
    public ArgsException(String str) {
        super(str);
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            MainFrame mf = new MainFrame("Calculate Series", new Dimension(1000, 400));
            mf.init();
        } catch (NumberFormatException e1) {
            System.out.println(e1.getMessage());
        }
    }
}