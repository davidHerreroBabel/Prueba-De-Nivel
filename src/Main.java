import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final int MINIMUM_VALUE_STRONG_PASSWORD = 8;
    private static final int MAXIMUM_SCORE = 9;
    private static final String AFFIRM_CONFIRM_SAVE_PASSWORD = "Si";

    public static void main(String[] args) {
        boolean passwordSaved = false;
        while (!passwordSaved) {
            System.out.println("Introduzca su contraseña:");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            int points = getPasswordPunctuation(password);
            passwordSaved = printResult(points, scanner);
        }
    }

    private static boolean printResult(int points, Scanner scanner) {
        System.out.println("El nivel de fortaleza de su contraseña es " + points + ".");
        if (points < MINIMUM_VALUE_STRONG_PASSWORD) {
            System.out.println("Está seguro de que quiere mantener una fortaleza de contraseña de nivel débil?");
            String confirmSavePassword = scanner.nextLine();
            if (confirmSavePassword.equals(AFFIRM_CONFIRM_SAVE_PASSWORD)) {
                System.out.println("Su contraseña ha sido aceptada.");
                return true;
            }
        }
        else {
            System.out.println("Su contraseña ha sido aceptada.");
            return true;
        }
        return false;
    }

    private static Integer getPasswordPunctuation(String password) {
        int points = getLength(password);

        Pattern patternLowercase = Pattern.compile("[a-z]");
        boolean containsLowercaseLetters = containsPattern(password, patternLowercase);

        Pattern patternUppercase = Pattern.compile("[A-Z]");
        boolean containsUppercaseLetters = containsPattern(password, patternUppercase);

        if (containsLowercaseLetters && containsUppercaseLetters) {
            points += 3;
        }
        else if (containsLowercaseLetters || containsUppercaseLetters) {
            ++points;
        }

        Pattern patternNumbers = Pattern.compile("[0-9]");
        boolean containsNumbers = containsPattern(password, patternNumbers);
        if (containsNumbers) {
            ++points;
        }

        Pattern patternSymbol = Pattern.compile("[^a-zA-Z0-9 ]");
        boolean containsSymbol = containsPattern(password, patternSymbol);
        if (containsSymbol) {
            points += 2;
        }

        if (points == MAXIMUM_SCORE) {
            ++points;
        }

        return points;
    }

    private static int getLength(String password) {
        int points = 0;
        if (password.length() > 6) {
            ++points;
            if (password.length() > 8) {
                ++points;
                if (password.length() > 12) {
                    ++points;
                }
            }
        }
        return points;
    }

    private static boolean containsPattern(String password, Pattern pattern) {
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}
