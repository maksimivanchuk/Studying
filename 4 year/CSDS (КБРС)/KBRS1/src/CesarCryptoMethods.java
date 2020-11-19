public class CesarCryptoMethods {
    protected static final int nEng = 26;
    protected static final int nRus = 33;

    protected static char[] engslish = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z' };

    protected static char[] russian = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж',
            'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
            'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я' };

    public static int findInEnglish(char c) {
        int rez = -1;

        for (int i = 0; i < engslish.length; ++i) {
            if (Character.toUpperCase(c) == engslish[i])
                rez = i;
        }
        return rez;
    }

    public static int findInRussian(char c) {
        int rez = -1;

        for (int i = 0; i < russian.length; ++i) {
            if (Character.toUpperCase(c) == russian[i])
                rez = i;
        }
        return rez;
    }

    public static boolean isRussian(char c){
        int i = (int) c;

        return (i>=1040 && i<=1103 || i == 1105 || i == 1025) ? true : false;
    }

    public static boolean isEnglish(char c){
        int i = (int) c;

        return ((i>=65 && i<=90  )|| (i>=97 && i<=122)) ? true : false;
    }

    public static String makeStr(int a[]){
        StringBuilder stringBuilder = new StringBuilder();
        String str = "" ;
        for ( int i = 0; i< a.length;i++)
            stringBuilder.append(engslish[(a[i]+26-4)%26]);
        return stringBuilder.toString();
    }
}
