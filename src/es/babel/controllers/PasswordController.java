package es.babel.controllers;

import es.babel.services.IPasswordService;
import es.babel.services.PasswordService;

public class PasswordController implements IPasswordController {
    private final IPasswordService passwordService = new PasswordService();

    @Override
    public void run() {
        boolean passwordSaved = false;
        while (!passwordSaved) {
            passwordService.printIntroducePassword();
            String password = passwordService.getValue();
            int passwordPunctuation = passwordService.getPasswordPunctuation(password);
            passwordSaved = passwordService.isPasswordSaved(passwordPunctuation);
        }
        passwordService.printAcceptedPassword();
    }

}
