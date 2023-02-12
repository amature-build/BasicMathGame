import java.util.Scanner;

public class GameUI {
    private final int LENGTH = 11;
    private final int WIDTH = 40;

    private Scanner _scan = new Scanner(System.in);

    /**
     * Method to create initial display screen for UI
     */
    public void welcome(){
        String[] msg = {"TheMathGame", "By Amature.Build"};
        boolean isGameTitle, isDevName, isGameTitleBlank, isDevNameBlank;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (j >=6 && j < WIDTH - 6 && i >= 2 && i < LENGTH - 2){

                    isGameTitle = i == 4 && j == 14;
                    isDevName = i == 6 && j == 12;
                    isGameTitleBlank = i == 4 && j > 14 && j < WIDTH - msg[0].length() - 4;
                    isDevNameBlank = i == 6 && j > 12 && j < WIDTH - msg[1].length() + 4;

                    if (isGameTitle){
                        System.out.print(msg[0]);
                    } else if (isGameTitleBlank) {
                        System.out.print("");
                    } else if (isDevName) {
                        System.out.print(msg[1]);
                    } else if (isDevNameBlank) {
                        System.out.print("");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
        promptWelcome();
    }

    /**
     * Method to ask user if want to start to game or exit.
     */
    private void promptWelcome(){
        String userInput;
        String[] possibleInputs = {"y", "Y"};
        System.out.println("Press y/Y to continue. Any other char to quit.");
        userInput = _scan.next();

        if (!userInput.toLowerCase().equals("y")){
            System.exit(0);
        }
    }

    /**
     * asks user to make input for a unique name
     * @return String validated username
     */
    public String promptUserName(){
        String userInput;
        boolean isInValidInput;

        do {
            System.out.println("Enter a Unique Name and Press ENTER");
            userInput = _scan.nextLine();
            isInValidInput = !validateUserName(userInput);
            if (isInValidInput){
                System.out.println("Incorrect input.\nDo not enter 0-9 and Space");
            }
        } while (isInValidInput);
        return userInput;
    }

    /**
     * Test each char in given argument to prevent numbers and space
     * @param userName String
     * @return boolean
     */
    private boolean validateUserName(String userName){
        String keyToIgnore = "[0-9 ]";

        for (int i = 0; i < userName.length(); i++) {
            String strChar = String.valueOf(userName.charAt(i));
            boolean matchCondition = strChar.matches(keyToIgnore);
            if(matchCondition){
                return false;
            }
        }
        return true;
    }
}
