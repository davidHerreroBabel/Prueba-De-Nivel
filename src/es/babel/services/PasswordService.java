package es.babel.services;

import es.babel.utils.InfoPrinter;
import es.babel.utils.InfoReader;
import es.babel.validators.PatternValidator;

public class PasswordService implements IPasswordService {
    private static final int MAXIMUM_SCORE = 9;
    private static final int MINIMUM_VALUE_STRONG_PASSWORD = 8;
    private static final String AFFIRM_CONFIRM_SAVE_PASSWORD = "Si";

    private static final InfoReader infoReader = new InfoReader();
    private static final InfoPrinter infoPrinter = new InfoPrinter();
    private final PatternValidator patternValidator = new PatternValidator();

    @Override
    public Integer getPasswordPunctuation(String password) {
        boolean containsLowercaseLetters = patternValidator.containsLowercaseCharacter(password);
        boolean containsUppercaseLetters = patternValidator.containsUppercaseCharacter(password);
        boolean containsNumbers = patternValidator.containsNumberCharacter(password);
        boolean containsSymbol = patternValidator.containsSymbolCharacter(password);
        return calculatePoints(password, containsLowercaseLetters, containsUppercaseLetters,
                containsNumbers, containsSymbol);
    }

    @Override
    public Boolean isPasswordSaved(int points) {
        infoPrinter.printStrongLevelPassword(points);
        if (points < MINIMUM_VALUE_STRONG_PASSWORD) {
            infoPrinter.printQuestionMaintainLowPasswordLevel();
            String confirmSavePassword = getValue();
            return confirmSavePassword.equals(AFFIRM_CONFIRM_SAVE_PASSWORD);
        }
        else {
            return true;
        }
    }

    @Override
    public String getValue() {
        return infoReader.readValue();
    }

    @Override
    public void printIntroducePassword() {
        infoPrinter.printIntroducePassword();
    }

    @Override
    public void printAcceptedPassword() {
        infoPrinter.printAcceptedPassword();
    }

    private static int calculatePoints(String password, boolean containsLowercaseLetters, boolean containsUppercaseLetters, boolean containsNumbers, boolean containsSymbol) {
        int points = calculateLengthPoints(password);
        points += calculateLettersPoints(containsLowercaseLetters, containsUppercaseLetters);
        points += calculateNumbersPoints(containsNumbers);
        points += calculateSymbolPoints(containsSymbol);
        points += calculateMaximumPoints(points);
        return points;
    }

    private static Integer calculateLengthPoints(String password) {
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

    private static Integer calculateLettersPoints(boolean containsLowercaseLetters, boolean containsUppercaseLetters) {
        int points = 0;
        if (containsLowercaseLetters && containsUppercaseLetters) {
            points = 3;
        }
        else if (containsLowercaseLetters || containsUppercaseLetters) {
            points = 1;
        }
        return points;
    }

    private static int calculateNumbersPoints(boolean containsNumbers) {
        int points = 0;
        if (containsNumbers) {
            ++points;
        }
        return points;
    }

    private static int calculateSymbolPoints(boolean containsSymbol) {
        int points = 0;
        if (containsSymbol) {
            points = 2;
        }
        return points;
    }

    private static int calculateMaximumPoints(int totalPoints) {
        int points = 0;
        if (totalPoints == MAXIMUM_SCORE) {
            ++points;
        }
        return points;
    }

}
