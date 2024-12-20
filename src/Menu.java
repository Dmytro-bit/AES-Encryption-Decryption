import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);


    public static void drawManu() {
        System.out.println("\nMenu options:");
        System.out.println("---------------------------------------");
        System.out.println("1) Encrypt a File");
        System.out.println("2) Decrypt a File");
        System.out.println("3) Exit");
        System.out.println("Select an option: \n");
    }

    public static void encryptMenu() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        String input;
        File file;


        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Please enter file name for encryption:");
            input = scanner.nextLine();
            file = new File(input);
            if (file.exists()) {
                break;
            }
            System.out.println("Selected file does not exist");
        }

        SecretKey key = AES.generateKey(256);
        AES.generateIv();
        File encrypted = new File("ciphertext.txt");
        AES.encryptFile(file, key, encrypted);

        System.out.println("---------------------------------------");
        System.out.println("\nFile encrypted successfully");
        System.out.println("Encryption key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
    }

    public static void decryptMenu() {
        String input;
        File file;

        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Please enter file name for decryption:");
            input = scanner.nextLine();
            file = new File(input);
            if (file.exists()) {
                break;
            }
            System.out.println("Selected file does not exist");
        }
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Please enter encryption key:");
            input = scanner.nextLine();

            try {

                byte[] decodedKey = Base64.getDecoder().decode(input);
                SecretKey key = new SecretKeySpec(decodedKey, "AES");

                File decrypted = new File("plaintext.txt");
                AES.decryptFile(key, file, decrypted);

                System.out.println("\nFile decrypted successfully");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
