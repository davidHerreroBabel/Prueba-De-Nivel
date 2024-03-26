package es.babel.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidator {

    public Boolean containsLowercaseCharacter(String password) {
        Pattern patternLowercase = Pattern.compile("[a-z]");
        return containsCharacter(password, patternLowercase);
    }

    public Boolean containsUppercaseCharacter(String password) {
        Pattern patternUppercase = Pattern.compile("[A-Z]");
        return containsCharacter(password, patternUppercase);
    }

    public Boolean containsNumberCharacter(String password) {
        Pattern patternNumbers = Pattern.compile("\\d");
        return containsCharacter(password, patternNumbers);
    }

    public Boolean containsSymbolCharacter(String password) {
        Pattern patternSymbol = Pattern.compile("[^a-zA-Z0-9 ]");
        return containsCharacter(password, patternSymbol);
    }

    private static boolean containsCharacter(String password, Pattern pattern) {
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}
