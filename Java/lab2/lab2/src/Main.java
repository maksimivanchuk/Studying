package com.company;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.String;
import java.util.Iterator;

class Calculate {
     StringBuffer str ;
     Map<	Character, Integer> hashMap = new HashMap<Character, Integer>();
     Calculate(StringBuffer s) {
        str = s;
    }
     void key_and_value() {
        for (int i = 0; i < str.length(); i++) {
            char s1 =str.charAt(i);
            Integer frequency = hashMap.get(s1);
            hashMap.put(s1, frequency == null ? 1 : frequency + 1);
        }
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("(" + mentry.getKey() + ";");
            System.out.println(mentry.getValue()+ ")");
        }
    }
}

public class Main {

    public static void main(String[] args) {
       try {
            StringBuffer s1 = new StringBuffer();
            s1.append(args[0]);
            System.out.println(s1);
            Calculate s = new Calculate(s1);
            s.key_and_value();
        }
      catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println("Exception!  There are no arguments! ");
        }
    }
}

