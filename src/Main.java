import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        Scanner scanner = new Scanner(System.in);
        boolean working = true;

        while (working) {
            Menu.drawManu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    Menu.encryptMenu();
                    break;
                case "2":
                    System.out.println("Encrypt");
                    Menu.decryptMenu();
                    break;
                case "3":
                    System.out.println("Ending program");
                    working = false;
                    break;
            }
        }
    }
}