package es.babel;

import es.babel.controllers.IPasswordController;
import es.babel.controllers.PasswordController;

public class Main {
    private static final IPasswordController passwordController = new PasswordController();

    public static void main(String[] args) {
        passwordController.run();
    }

}
