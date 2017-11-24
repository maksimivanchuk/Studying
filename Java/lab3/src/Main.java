import java.io.File;
import java.io.IOException;
import java.util.*;

class ArgsException extends Exception {
    public ArgsException(String str) {
        super(str);
    }
}

public class Main {
    static int aHeight, aWidth;

    public static void scalar_operation(Integer aHeight, Integer aWidth, Double ma[][]) {

        for (int x = 0; x < aHeight; x++) {
            for (int y = 0; y < aWidth; y++)
                System.out.print(ma[x][y] + "   ");
            System.out.println("  ");
        }
        System.out.println("  ");

        ArrayList  Min = new  ArrayList ();
        ArrayList  Max = new ArrayList ();
        Double max = ma[0][0], min = ma[0][0];

        for (int x = 0; x < aHeight; x++) {
            for (int y = 0; y < aWidth; y++)
            {
                if (ma[x][y] <= min) {
                    if (ma[x][y] < min) {
                        Min.clear();
                        min = ma[x][y];
                    }
                    Min.add(x);
                }
                if (ma[x][y] >= max) {
                    if (ma[x][y] >max) {
                        Max.clear();
                        max = ma[x][y];
                    }
                    Max.add(y);
                }
            }
        }

        Set set_min = new TreeSet(Min);
        Set set_max = new TreeSet(Max);
        Min = new  ArrayList (set_min);
        Max = new ArrayList (set_max);

        double sum = 0;
        for (int x = 0; x < Min.size(); x++) {
            for (int z = 0; z < Max.size(); z++) {
                for (int y = 0; y < aHeight; y++) {
                    sum += ma[(Integer)Min.get(x)][y] * ma[y][(Integer)Max.get(z)];
                }
                System.out.print("Scalar  operation  " +((Integer)Min.get(x)+1)  + " string and " + ((Integer)Max.get(z)+1) + " column  = " + sum);
                System.out.println("");
                sum = 0;
            }
        }
    }

    public static void main(String args[]) {
        try {
            Double[][] ma;
            Scanner sc = new Scanner(new File("D://Java//lab3//input.txt"));
            aHeight = sc.nextInt();
            aWidth = sc.nextInt();
            if (aHeight != aWidth)
                throw new ArgsException("Exception! The number of elements in the vectors does not coincide");
            ma = new Double[aHeight][aWidth];
            String str = "";
            int row = 0;

            for (int i = 0; i < aHeight; i++) {
                for (int j = 0; j < aHeight; j++) {

                    if (sc.hasNextDouble())
                        ma[i][j] = sc.nextDouble();
                    else
                        throw new ArgsException("Exception! The elements not Double");
                }
            }
            scalar_operation(aHeight, aWidth, ma);
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        } catch (ArgsException e1) {
            System.out.println(e1.getMessage());
        }
    }
}
