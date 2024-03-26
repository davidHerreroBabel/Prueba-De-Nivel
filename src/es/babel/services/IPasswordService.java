package es.babel.services;

public interface IPasswordService {

    Integer getPasswordPunctuation(String password);

    Boolean isPasswordSaved(int points);

    String getValue();

    void printIntroducePassword();

    void printAcceptedPassword();

}
