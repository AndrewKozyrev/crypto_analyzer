import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CaesarCipherTests {

    public static Stream<Arguments> providerEncrypt() {
        return Stream.of(
                Arguments.of("привет, мир!", "тулезх-вплуа", 3),
                Arguments.of("этот мир такой вкусный!", "нг гч!,бчгш: ”чъ:дв?л”х", 25),
                Arguments.of("семь раз отмерь - один отрежь", "тёнэасбиапунёсэа!апейоапусёзэ", 1)
        );
    }

    public static Stream<Arguments> providerDecrypt() {
        return Stream.of(
                Arguments.of("тулезх-вплуа", "привет, мир!", 3),
                Arguments.of("нг гч!,бчгш: ”чъ:дв?л”х", "этот мир такой вкусный!", 25),
                Arguments.of("тёнэасбиапунёсэа!апейоапусёзэ", "семь раз отмерь - один отрежь", 1),
                Arguments.of("йб-бкщяё.-л”.!кйчщякибя-ёчя”яд.зкбяж,л”.!кйчш", "сидит, надувается, три дня в лапти обувается.", 33)
        );
    }

    public static Stream<Arguments> providerHackCipher() {
        return Stream.of(
                Arguments.of("тулезх-вплуа", "привет, мир!"),
                Arguments.of("нг гч!,бчгш: ”чъ:дв?л”х", "этот мир такой вкусный!"),
                Arguments.of("тёнэасбиапунёсэа!апейоапусёзэ", "семь раз отмерь - один отрежь"),
                Arguments.of("йб-бкщяё.-л”.!кйчщякибя-ёчя”яд.зкбяж,л”.!кйчш", "сидит, надувается, три дня в лапти обувается.")
        );
    }

    @ParameterizedTest
    @MethodSource("providerEncrypt")
    void encryptTest(String text, String expected, int key) {
        var actual = CaesarCipher.encrypt(text, key);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("providerDecrypt")
    void decryptTest(String encodedText, String expected, int key) {
        var actual = CaesarCipher.decrypt(encodedText, key);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("providerHackCipher")
    void hackCipherTest(String encodedText, String expected) {
        var actual = CaesarCipher.hackCipher(encodedText);
        Assertions.assertEquals(expected, actual);
    }
}
