import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final int MINIMUM_VALUE_STRONG_PASSWORD = 8;
    private static final int MAXIMUM_SCORE = 9;

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Introduzca su contraseña:");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            int points = setPunctuation(password);
            exit = printResult(points, scanner);
        }
    }

    private static boolean printResult(int points, Scanner scanner) {
        System.out.println("El nivel de fortaleza de su contraseña es " + points + ".");
        if (points < MINIMUM_VALUE_STRONG_PASSWORD) {
            System.out.println("Está seguro de que quiere mantener una fortaleza de contraseña de nivel débil?");
            String confirm = scanner.nextLine();
            if (confirm.equals("Si")) {
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

    private static Integer setPunctuation(String password) {
        int points = setLength(password);

        Pattern patternLowercase = Pattern.compile("[a-z]");
        boolean containsLowercase = checkMatch(password, patternLowercase);

        Pattern patternUppercase = Pattern.compile("[A-Z]");
        boolean containsUppercase = checkMatch(password, patternUppercase);

        if (containsLowercase && containsUppercase) {
            points += 3;
        }
        else if (containsLowercase || containsUppercase) {
            ++points;
        }

        Pattern patternNumbers = Pattern.compile("[0-9]");
        boolean containsNumbers = checkMatch(password, patternNumbers);
        if (containsNumbers) {
            ++points;
        }

        Pattern patternSymbol = Pattern.compile("[^a-zA-Z0-9 ]");
        boolean containsSymbol = checkMatch(password, patternSymbol);
        if (containsSymbol) {
            points += 2;
        }

        if (points == MAXIMUM_SCORE) {
            ++points;
        }

        return points;
    }

    private static int setLength(String password) {
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

    private static boolean checkMatch(String password, Pattern pattern) {
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}