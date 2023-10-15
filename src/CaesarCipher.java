import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class CaesarCipher {
    public static final String alphabet = "";

    public static String encrypt(String filePath, int key) {return null;}

    public static String decrypt(String filePath, int key) {return null;}

    public static String hackCipher(String filePath) {return null;}

    public static void main(String[] args) throws IOException {
//        String content = Files.readString(Paths.get("src/CaesarCipher.java"));
        BufferedReader reader = new BufferedReader(new FileReader("src/CaesarCipher.java"));
        reader.lines().forEach(System.out::println);
    }
}
