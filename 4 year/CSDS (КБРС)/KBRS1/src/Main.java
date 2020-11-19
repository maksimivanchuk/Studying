import Kasiski.ClassicKasiskiTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Collections.*;

public class Main extends CesarCryptoMethods{
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner1 = new Scanner(new File("file1.txt"));
        String line1 = scanner1.nextLine()+"\n";
        while (scanner1.hasNextLine()) {
            line1 += scanner1.nextLine()+"\n";
        }

        EncryptCesar enc = new EncryptCesar(line1, 7);

        System.out.println(enc.getEncText());

        DecryptionCesar dec = new DecryptionCesar(enc);
        System.out.println(dec.getDecText());


        Scanner scanner2 = new Scanner(new File("file2.txt"));
        String line2 = scanner2.nextLine()+"\n";
        while (scanner2.hasNextLine()) {
            line2 += scanner2.nextLine()+"\n";
        }

        EncryptCesar encRomeo = new EncryptCesar(line2, 5);

        System.out.println(encRomeo.getEncText());

        DecryptionCesar decRomeo = new DecryptionCesar(encRomeo);
        System.out.println(decRomeo.getDecText());


        Scanner scanner3 = new Scanner(new File("file3.txt"));
        String key = scanner3.nextLine() + "\n";
        String line3 = scanner3.nextLine() + "\n";
        while (scanner3.hasNextLine()) {
            line3 += scanner3.nextLine() + "\n";
        }
        System.out.println(line3);
        line3 = line3.substring(0,2000);
        Vijiner encr = new Vijiner(line3, key);

       // System.out.println(encr.getEncText());
        System.out.println(encr.getEncText().length());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i =2; i <= 9; i++) { //(line3.length() / 100)
            ClassicKasiskiTest test = new ClassicKasiskiTest(encr.getOut(), i);
            Integer count = map.get(test.test());
            map.put(test.test(), count != null ? count + 1 : 0);
        }

        map.remove(0);
        Integer popular = max(map.entrySet(),
                Comparator.comparing(Map.Entry::getValue)).getKey();
          System.out.println(popular);


        int[][] wordPopular=  new int[2][popular];
        int[][] a = new int[popular][26];
        for (int i = 0, j = 0; i < encr.getOut().length(); ++i) {
            int word = findInEnglish(encr.getOut().charAt(i));
            a[j][word] += 1;
            j = j + 1;
            j = j % popular;
        }

        for (int i = 0; i < popular; i++) {
            for (int j = 0; j < 26; j++) {
                if( wordPopular[1][i]<a[i][j]) {
                    wordPopular[0][i] = j;
                    wordPopular[1][i] = a[i][j];
                }
            }
        }

        String str = makeStr(wordPopular[0]);
        System.out.println(str);
        DecryptVijiner decryptVijiner = new DecryptVijiner(line3,str);
       //System.out.println(decryptVijiner.getText());

    }


}