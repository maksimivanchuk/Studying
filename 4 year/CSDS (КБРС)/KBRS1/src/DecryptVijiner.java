public class DecryptVijiner extends CesarCryptoMethods{
    private String output;
    private String key;


    public DecryptVijiner(String Message, String Key){
        this.key = Key;
        String input = Message;
        StringBuilder output = new StringBuilder();

        for (int i = 0, j = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (isEnglish(c)) {
                int y =  (findInEnglish(c) - findInEnglish(Key.charAt(j))+26) % nEng ;
                j=j+1 ;
                if( j>1 )
                 j = j% (Key.length()-1);
                else
                    j = j%Key.length();
                if (Character.isUpperCase(c)) {
                    output.append(engslish[y]);
                } else {
                    output.append(Character.toLowerCase(engslish[y]));
                }
                continue;
            }

            if (!isEnglish(c)) {
                output.append(c);
            }
        }
        this.output = output.toString();
    }

    public String getText() {

        return this.output;
    }

    public String getKey() {

        return this.key;
    }

}
