import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CaesarCipherTests {

    @Test
    void encryptTest() {
        int key = 3;
        String text = "привет, мир!";
        String expected = "супзи-вплуа";
        String actual = CaesarCipher.encrypt(text, key);
        Assertions.assertEquals(expected, actual);
    }
}
