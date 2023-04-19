package rockpaperscissors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        game();
    }


    public static void game() {
        Scanner scanner = new Scanner(System.in);
        String userOption = scanner.nextLine();
        String compOption = Option.valueOf(userOption.toUpperCase()).loses.toLowerCase();

        System.out.println("Sorry, but the computer chose " + compOption);
    }
}
