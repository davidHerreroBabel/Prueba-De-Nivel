package es.babel.utils;

import java.util.Scanner;

public class InfoReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readValue() {
        return scanner.nextLine();
    }

}
