package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.String;
import java.util.Scanner;
import java.util.Iterator;

class ArgsException extends Exception {
    public ArgsException(String str) {
        super(str);
    }
}

class Calculate {
    public String str = "";
    public Map<String, Integer> hashMap = new HashMap<String, Integer>();
    public Calculate() { }
    public Calculate(String s) {
        str = s;
    }
    public void key_and_value() {
        for (int i = 0; i < str.length(); i++) {
            String s1 = String.valueOf(str.charAt(i));
            Integer frequency = hashMap.get(s1);
            hashMap.put(s1, frequency == null ? 1 : frequency + 1);
        }
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("Symbol is: " + mentry.getKey() + ";  Reapet is: ");
            System.out.println(mentry.getValue());
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1;
        if (sc.hasNextBigInteger() && sc.hasNextInt()&&sc.hasNextLong())
            System.out.println(" Error ");
        else
        {
            s1 = sc.nextLine();
            Calculate s = new Calculate(s1);
            s.key_and_value();
        }
    }
}

