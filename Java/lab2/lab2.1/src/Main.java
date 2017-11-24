class Calculate {
    StringBuffer str;
    Calculate() { }
    Calculate(StringBuffer s) {
        str = s;
    }
    void value_and_reapet() {
        for (int i = 0; i < str.length(); i++) {
            char s1 = str.charAt(i);
            int counter = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (s1 == str.charAt(j)) {
                    str.deleteCharAt(j);
                    counter++;
                    j--;
                }
            }
            System.out.println("Symbol is: " + str.charAt(i) + ";  Reapet is: " + counter);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        s1.append(args[0]);
        System.out.println();
        System.out.println(s1);
        Calculate s = new Calculate(s1);
        s.value_and_reapet();
    }
}

