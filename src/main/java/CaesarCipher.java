import org.languagetool.JLanguageTool;
import org.languagetool.language.Russian;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    static final JLanguageTool langTool = new JLanguageTool(new Russian());
    static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

    static final String PUNCTUATION = "[.,”:\\-!?]+";

    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int charPosition = ALPHABET.indexOf(c);
            int encryptedIndex = (charPosition + key) % ALPHABET.length();
            char encryptedSymbol = ALPHABET.charAt(encryptedIndex);
            encryptedText.append(encryptedSymbol);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int charPosition = ALPHABET.indexOf(c);
            int keyVal = (charPosition - key + ALPHABET.length()) % ALPHABET.length();
            char replaceVal = ALPHABET.charAt(keyVal);
            decryptedText.append(replaceVal);
        }

        return decryptedText.toString();
    }

    public static String hackCipher(String text) {
        var list = new ArrayList<String>();
        for (int key = 0; key < ALPHABET.length(); key++) {
            var decryptedText = decrypt(text, key);
            var decryptedTextWithoutPunctuation = decryptedText.replaceAll(PUNCTUATION, "");
            boolean isValid = true;
            for (String word : decryptedTextWithoutPunctuation.split("\\s")) {
                if (!checkSpelling(word)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                list.add(decryptedText);
            }
        }

        return list.get(0);
    }

    public static boolean checkSpelling(String text) {

        List<RuleMatch> matches = null;

        try {
            matches = langTool.check(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches.isEmpty();
    }
}
