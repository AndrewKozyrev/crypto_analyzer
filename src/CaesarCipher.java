import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CaesarCipher {

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    ;
    private static final Set<String> ERROR_PATTERNS = Set.of("[а-яА-ЯёЁ\\W]+[:;”?!]+[а-яА-ЯёЁ\\W]+");
    private static final String baseUrl = "https://speller.yandex.net/services/spellservice.json/checkText?text=";
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

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
            if (isValidText(decryptedText)) {
                list.add(decryptedText);
            }
        }
        IntStream.range(1, list.size()).forEach(key -> System.out.printf("%d. %s%n", key, list.get(key - 1)));

        return list.get(0);
    }

    private static boolean isValidText(String text) {
        boolean isInvalid;
        for (var errorPattern : ERROR_PATTERNS) {
            var pattern = Pattern.compile(errorPattern);
            var matcher = pattern.matcher(text);
            isInvalid = matcher.find();
            if (isInvalid) {
                return false;
            }
        }
        String encodedQueryParam = URLEncoder.encode(text, StandardCharsets.UTF_8);
        var url = baseUrl + encodedQueryParam;
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept-Charset", "UTF-8")
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response.body().equals("[]");
    }
}
