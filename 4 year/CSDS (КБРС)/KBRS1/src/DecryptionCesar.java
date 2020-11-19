public class DecryptionCesar extends CesarCryptoMethods {
    private String output;

    public DecryptionCesar(String text, int k) {

        String input = text;
        StringBuilder dec = new StringBuilder();

        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);

            if (isEnglish(c)) {
                int x = (findInEnglish(c) - k + nEng) % nEng;

                if (Character.isUpperCase(c)) {
                    dec.append(engslish[x]);
                } else {
                    dec.append(Character.toLowerCase(engslish[x]));
                }

                continue;
            }

            if (isRussian(c)) {
                int x = (findInRussian(c) - k + nRus) % nRus;

                if (Character.isUpperCase(c)) {
                    dec.append(russian[x]);
                } else {
                    dec.append(Character.toLowerCase(russian[x]));
                }

                continue;
            }

            if (!isEnglish(c) && !isRussian(c)) {
                dec.append(c);
            }
        }

        output = dec.toString();
    }

    public DecryptionCesar(EncryptCesar enc) {
        int k = enc.getK();
        String input = enc.getEncText();

        DecryptionCesar dec = new DecryptionCesar(input, k);
        output = dec.getDecText();

    }

    public String getDecText() {
        return output;
    }

}