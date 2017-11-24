import javax.swing.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class Series {
    protected double a0;
    protected  double q;
    protected int n;

    public Series(double a0, double q) {
        this.a0 = a0;
        this.q = q;
        this.n= 3;
    }

    public Series(double a0, double q, int n) {
        this.a0 = a0;
        this.q = q;
        this.n = n;
    }

    public  abstract  double  getAn (int n);
    public double getSum() {
     double sum = 0;

     for (int i=0;i<n;i++) {
         sum += getAn(i);
     }
     return sum;
    }

    public String toString(){
        StringBuilder s = new StringBuilder("  ");
        for (int i=0;i<n;i++) {
            s.append(getAn(i)+", ") ;
        }
        s.delete(s.length()-2,s.length());

        return String.valueOf(s);
    }

    public void FileRecoder(String str){
        {
            JFileChooser fc = new JFileChooser(new File("."));
            //fc.setCurrentDirectory(new java.io.File("D:/Java"));
            if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
                    fw.write(str);
                }
                catch ( IOException e ) {
                    System.out.println(" Exeption!!! ");
                }
            }
        }

    }


}


class Liner extends Series {
    public Liner(double a0, double q) {
        super(a0, q);
    }

    public Liner(double a0, double q, int n) {
        super(a0, q, n);
    }

    @Override
    public double getAn(int n) {
        return this.a0+(n*this.q);
    }
}

class Exponential extends  Series{
    public Exponential(double a0, double q) {
        super(a0, q);
    }

    public Exponential(double a0, double q, int n) {
        super(a0, q, n);
    }

    @Override
    public double getAn(int n) {
        return  a0* Math.pow(this.q,n);
    }
}