package es.babel.utils;

public class InfoPrinter {

    public void printIntroducePassword() {
        System.out.println("Introduzca su contraseña:");
    }

    public void printQuestionMaintainLowPasswordLevel() {
        System.out.println("Está seguro de que quiere mantener una fortaleza de contraseña de nivel débil?");
    }

    public void printStrongLevelPassword(Integer points) {
        System.out.println("El nivel de fortaleza de su contraseña es " + points + ".");
    }

    public void printAcceptedPassword() {
        System.out.println("Su contraseña ha sido aceptada.");
    }

}
