import java.util.Scanner;

public class GameUI {
    private final int LENGTH = 13;
    private final int WIDTH = 40;

    private final int INNER_SQUARE_WIDTH_START = 6;
    private final int INNER_SQUARE_LENGTH_START = 2;

    private Scanner _scan = new Scanner(System.in);

    /**
     * Method to create initial display screen for UI
     */
    public void welcome(){
        String[] msg = {"TheMathGame", "By Amature.Build"};
        boolean isGameTitle, isDevName, isGameTitleBlank, isDevNameBlank;
        int gameTitleStart = 14;
        int devNameStart = 12;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (isBlankWelcome(i, j)){

                    isGameTitle = i == 5 && j == gameTitleStart;
                    isDevName = i == 7 && j == devNameStart;
                    isGameTitleBlank = i == 5 && j > gameTitleStart && j < msg[0].length() + gameTitleStart;
                    isDevNameBlank = i == 7 && j > devNameStart && j < msg[1].length() + devNameStart;

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
        System.out.println("Press y/Y to continue. Any other char to quit.");
        userInput = _scan.next();

        if (!userInput.equalsIgnoreCase("y")){
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
        return !validate(keyToIgnore, userName);
    }

    /**
     * Display possible selection for the MathGame
     */
    public void problemSelection(){
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (isBlankSelection(i) && j == 0){
                    String output = isProblemOption(i);
                    System.out.print(output);
                } else if (!isBlankSelection(i)){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        promptSelection();
    }

    /**
     * Validate given word against given pattern
     * @param patterns takes in patterns to validate users input
     * @param toTest takes in users input to test again given patterns
     * @return boolean
     */
    private boolean validate(String patterns, String toTest){
        for (int i = 0; i < toTest.length(); i++) {
            String strChar = String.valueOf(toTest.charAt(i));
            boolean matchCondition = strChar.toLowerCase().matches(patterns);
            if(matchCondition){
                return true;
            }
        }
        return false;
    }

    /**
     * With pattern as [12345n]. Validate if user input is not in given pattern
     * @param userInput users input from scanner
     * @return boolean
     */
    private boolean validateSelectionInput(String userInput){
        String keyToIgnore = "^[12345n]";

        return validate(keyToIgnore, userInput);
    }

    /**
     * Promps user to make selection to game.
     * If validated and correct, output the user input.
     * @return validated user input
     */
    private String promptSelection(){
        String userInput;
        boolean isInValidInput;
        do {
            userInput = _scan.nextLine();
            isInValidInput = !validateSelectionInput(userInput);
            if (isInValidInput){
                System.out.println("Invalid input. Please try again.");
            }
            promptSelectionQuit(userInput);
        } while (isInValidInput);

        return userInput;
    }

    private void promptSelectionQuit(String strInput){
        if (strInput.equalsIgnoreCase("n")){
            System.exit(0);
        }
    }
    private boolean isBlankWelcome(int x, int y){
        boolean boolInnerSquareWidth = y >= INNER_SQUARE_WIDTH_START && y < WIDTH - INNER_SQUARE_WIDTH_START;
        boolean boolInnerSquareLength = x >= INNER_SQUARE_LENGTH_START && x < LENGTH - INNER_SQUARE_LENGTH_START;
        return boolInnerSquareLength && boolInnerSquareWidth;
    }

    private boolean isBlankSelection(int x){
        boolean boolInnerSquareLength = x >= INNER_SQUARE_LENGTH_START && x < LENGTH - INNER_SQUARE_LENGTH_START;
        return boolInnerSquareLength;
    }

    /**
     * Lazy way to output menu selection
     * @param x where the output should be
     * @return the output
     */
    private String isProblemOption(int x){
        String msg;
        switch (x) {
            case 2 -> msg = "******     CHOOSE A PROBLEM       ******";
            case 4 -> msg = "******  1. ADD                    ******";
            case 5 -> msg = "******  2. SUBTRACT               ******";
            case 6 -> msg = "******  3. MULTIPLY               ******";
            case 7 -> msg = "******  4. DIVIDE                 ******";
            case 8 -> msg = "******  5. STATS                  ******";
            case 9 -> msg = "******  n/N to QUIT               ******";
            default -> msg = "******                            ******";
        }
        return msg;
    }

}
