import javax.swing.*;
import java.io.*;

public abstract class Series {
    double a0;
    double q;
    private int n;

    Series(double a0, double q) {
        this.a0 = a0;
        this.q = q;
        this.n= 3;
    }

    Series(double a0, double q, int n) {
        this.a0 = a0;
        this.q = q;
        this.n = n;
    }

    public  abstract  double  getAn (int n);
    double getSum() {
        double sum = 0;

        for (int i=0;i<n;i++) {
            sum += getAn(i);
        }
        return sum;
    }

    public String toString(){
        StringBuilder s = new StringBuilder("  ");
        for (int i=0;i<n;i++) {
            s.append(getAn(i)).append(", ");
        }
        s.delete(s.length()-2,s.length());

        return String.valueOf(s);
    }

    void save ( File file )
    {
        try ( FileWriter fw = new FileWriter(file) ) {
            fw.write(this.toString());
        }
        catch ( IOException e ) {
            System.out.println(" Exeption!!! ");
        }
    }
}


class Liner extends Series {
    public Liner(double a0, double q) {
        super(a0, q);
    }

    Liner(double a0, double q, int n) {
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

    Exponential(double a0, double q, int n) {
        super(a0, q, n);
    }

    @Override
    public double getAn(int n) {
        return  a0* Math.pow(this.q,n);
    }
}