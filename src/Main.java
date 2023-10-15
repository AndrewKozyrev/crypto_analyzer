import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the source text file: ");
        String path = scanner.nextLine();

        System.out.print("Enter the encryption key: ");
        int key = scanner.nextInt();

        try {
            File inputFile = new File(path);
            File outputFile = new File("encrypted.txt");
            FileReader reader = new FileReader(inputFile);
            FileWriter writer = new FileWriter(outputFile);

            BufferedReader bufferedReader = new BufferedReader(reader);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String encryptedLine = CaesarCipher.encrypt(line, key);
                bufferedWriter.write(encryptedLine);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}