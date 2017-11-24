package com.company;

class ArgsException extends Exception {
    public ArgsException(String str) {
        super(str);
    }

}

class Sum {
    public double eps;
    public Double X;

    public Sum() {
        eps = 0;
        X = 0.0;
    }

    public Sum(Double Y, double eps1) {
        eps = eps1;
        X = Y;
    }

    public double calculate_sum() {
        double sum = 0, counter = 1, numerator = X, denominator = 1, pred = 1;

        while (Math.abs(pred) > eps) {
            numerator += Math.pow(counter, -0.5);
            denominator *= ++counter;
            pred = numerator / denominator;
            sum += numerator / denominator;
            numerator = X;
        }
        return sum;
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 2)
                throw new ArgsException(" Incorrect arguments");
            Sum s = new Sum(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
            System.out.printf("Сумма ряда = %.10f\n", s.calculate_sum());
        } catch (ArgsException e1) {
            System.out.println(e1.getMessage());
        } catch (IllegalArgumentException e2) {
            System.out.println("Exception! Program arguments must be double!");
        }
    }
}

