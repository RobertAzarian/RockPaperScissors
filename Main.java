package rockpaperscissors;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static HashMap<String, Integer> playersBase = getRatingBaseFromTheFile();

    public static void main(String[] args) {
        game();
    }


    public static void game() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String nameOfUser;
        int exitStatusName;
        do {
            System.out.print("Enter your name: ");
            nameOfUser = scanner.nextLine();
            exitStatusName = isNameExists(nameOfUser);
            if (exitStatusName == 0) {
                System.out.println("Buy!");
                return;
            }
        } while (exitStatusName != 2);      // exitStatus: 0 - exit, 1 - false, 2 - true;

        do {
            String input = scanner.nextLine();

            int exitStatus = inputProcessing(input, nameOfUser);
            if (exitStatus == 0) {
                System.out.println("Bye!");
                return;
            } else if (exitStatus == 1) {
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
                playersBase.replace(nameOfUser, playersBase.get(nameOfUser) + 50);
            } else if (userOption.wins.equals(cNameOption)) {
                System.out.printf("Well done. The computer chose %s and failed\n", cNameOption);
                playersBase.replace(nameOfUser, playersBase.get(nameOfUser) + 100);
            } else if (userOption.loses.equals(cNameOption)) {
                System.out.printf("Sorry, but the computer chose %s\n", cNameOption);
            } else {
                System.out.println("error");
            }
        } while (true);
    }



    public static int isNameExists(String input) {
        if (input.equals("!exit")) {
            return 0;
        }

        boolean isNameExists = false;
        for (String nameFromBase : playersBase.keySet()) {
            if (input.equals(nameFromBase)) {
                isNameExists = true;
                System.out.println("Hello, " + input);
                break;
            }
        }
        if (!isNameExists) {
            System.out.println("Invalid name input");
            return 1;
        }
        return 2;
    }


    public static int inputProcessing(String input) {
        if (input.equals("!exit")) {
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


    public static int inputProcessing(String input, String name) {
        if ("!rating".equals(input)) {
            int rating = playersBase.get(name);
            System.out.println("Your rating: " + rating);
        } else {
            return inputProcessing(input);
        }
        return 1;
    }


    public static HashMap<String, Integer> getRatingBaseFromTheFile() {
        HashMap<String, Integer> playersBase = new HashMap<>();
        try (FileReader ratingFile = new FileReader("rating.txt")) {
            Scanner scanFile = new Scanner(ratingFile);
            while (scanFile.hasNextLine()) {
                playersBase.put(scanFile.next(), scanFile.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playersBase;
    }
}
