package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        game();
    }


    public static void game() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        do {
            String input = scanner.nextLine();

            int codeOfChecking = checkingInput(input);
            if (codeOfChecking == 0) {
                break;
            } else if (codeOfChecking == 1) {
                continue;
            }

            Option userOption = Option.valueOf(input.toUpperCase());
            Option compOption;
            int randOption = random.nextInt(1, 4);
            switch (randOption) {
                case 1 -> compOption = Option.PAPER;
                case 2 -> compOption = Option.SCISSORS;
                default -> compOption = Option.ROCK;
            }

            String uNameOption = userOption.name().toLowerCase();
            String cNameOption = compOption.name().toLowerCase();

            if (userOption == compOption) {
                System.out.printf("There is a draw (%s)\n", uNameOption);
            } else if (userOption.wins.equals(cNameOption)) {
                System.out.printf("Well done. The computer chose %s and failed\n", cNameOption);
            } else if (userOption.loses.equals(cNameOption)) {
                System.out.printf("Sorry, but the computer chose %s\n", cNameOption);
            } else {
                System.out.println("error");
            }
        } while (true);
    }

    public static int checkingInput(String input) {
        if (input.equals("!exit")) {
            System.out.println("Bye!");
            return 0;
        }

        for (Option option : Option.values()) {
            if (input.toUpperCase().equals(option.name())) {
                return 2;
            }
        }
        System.out.println("Invalid input");
        return 1;
    }
}
