package rockpaperscissors;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static HashMap<String, Integer> playersBase = getRatingBaseFromTheFile();
    static ArrayList<String> parametersBase;

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


        parametersBase = new ArrayList<>();
        parametersBase.add("rock");
        parametersBase.add("paper");
        parametersBase.add("scissors");

        do {
            String input = scanner.nextLine();


            int exitStatus = inputProcessing(input, nameOfUser);
            if (exitStatus == 0) {
                System.out.println("Bye!");
                return;
            } else if (exitStatus == 1) {
                continue;
            }


            int countOfParams = parametersBase.size();
            int userIndex = parametersBase.indexOf(input);
            int compIndex = random.nextInt(0, countOfParams);

            //     userOption = input
            String compOption = parametersBase.get(compIndex);

            int userNewPosit = userIndex;   // comparison of the position of the computer from the center
            int compNewPosit = compIndex;   //

            int cnt = countOfParams / 2;
            int steps = 0;
            while (userNewPosit != cnt) {
                if (userNewPosit == countOfParams - 1) {
                    userNewPosit = 0;
                } else {
                    userNewPosit++;
                }
                steps++;
            }
            for (int i = 0; i < steps; i++) {
                if (compNewPosit == countOfParams - 1) {
                    compNewPosit = 0;
                } else {
                    compNewPosit++;
                }
            }

            if (userIndex == compIndex) {
                System.out.printf("There is a draw (%s)\n", input);
                playersBase.replace(nameOfUser, playersBase.get(nameOfUser) + 50);
            } else if (compNewPosit < userNewPosit){
                System.out.printf("Well done. The computer chose %s and failed\n", compOption);
                playersBase.replace(nameOfUser, playersBase.get(nameOfUser) + 100);
            } else if (compNewPosit > userNewPosit) {
                System.out.printf("Sorry, but the computer chose %s\n", compOption);
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

        String[] parInpArr = input.split(",");      // setting parameters
        if (parInpArr.length > 2 && parInpArr.length % 2 == 1) {
            parametersBase.clear();
            parametersBase.addAll(Arrays.asList(parInpArr));
            return 1;
        }

        for (String option : parametersBase) {      // checking name of parameters
            if (input.equals(option)) {
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
