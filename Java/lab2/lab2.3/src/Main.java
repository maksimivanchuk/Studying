class ArgsException extends Exception {
    public ArgsException(String str) {
        super(str);
    }
}
class Calculate {
    StringBuffer str;

    Calculate() {}
    Calculate(StringBuffer s ){
        str=s;
    }
    void key_and_value(){
        for(int i=0; i < str.length(); i++)
        {
            char s1= str.charAt(i);
            int counter=1;

            for (int j=i+1;j< str.length(); j++){
                if(s1==str.charAt(j)) {
                    str.deleteCharAt(j);
                    counter++;
                    j--;
                }
            }
            System.out.println("Symbol is: "+str.charAt(i) + ";  Reapet is: " + counter);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            StringBuffer s1 = new StringBuffer();
            s1.append(args[0]);
            System.out.println();
            if (s1.length() == 0)
                throw new ArgsException("Incorrect arguments");
            else
            {
                Calculate s = new Calculate(s1);
                s.key_and_value();
            }
        }
        catch (ArgsException e1) {
            System.out.println(e1.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println("Exception!  There are no arguments! ");
        }
    }
}

