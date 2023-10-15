public class CaesarCipher {
    public static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int charPosition = alphabet.indexOf(c);
            int keyVal = (key + charPosition) % alphabet.length();
            char replaceVal = alphabet.charAt(keyVal);
            encryptedText.append(replaceVal);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int charPosition = alphabet.indexOf(c);
            int keyVal = (charPosition - key + alphabet.length()) % alphabet.length();
            char replaceVal = alphabet.charAt(keyVal);
            decryptedText.append(replaceVal);
        }

        return decryptedText.toString();
    }

    public static String hackCipher(String text) {return null;}
}
