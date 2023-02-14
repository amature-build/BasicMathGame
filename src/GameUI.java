import java.util.Scanner;

public class GameUI {
    private final int LENGTH = 13;
    private final int WIDTH = 40;

    private final int INNER_SQUARE_WIDTH_START = 6;
    private final int INNER_SQUARE_LENGTH_START = 2;

    private String _toContinue;
    private String _userInput;
    private int[] _problem;
    public GameUI() {
    }

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
     * Method to ask user whether to start to game or exit.
     */
    private void promptWelcome(){
        System.out.println("Press y/Y to continue. Any other char to quit.");
        Scanner scan = new Scanner(System.in);
        this._userInput = scan.nextLine();

        if (!this._userInput.equalsIgnoreCase("y")){
            System.exit(0);
        }
    }

    /**
     * asks user to make input for a unique name
     */
    public void promptUserName(){
        boolean isInValidInput;
        do {
            System.out.println("Enter a Unique Name and Press ENTER");
            Scanner scan = new Scanner(System.in);
            this._userInput = scan.nextLine();
            isInValidInput = !validateUserName(this._userInput);
            if (isInValidInput){
                System.out.println("Incorrect input.\nDo not enter 0-9 and Space");
            }
        } while (isInValidInput);
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
                if (isBlank(i) && j == 0){
                    String output = isProblemOption(i);
                    System.out.print(output);
                } else if (!isBlank(i)){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
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

    public void promptSolution(){
        boolean isNegative;
        do {
            System.out.println("Please enter the answer.");
            Scanner scan = new Scanner(System.in);
            this._userInput = scan.nextLine();
            isNegative = !validateUserAnswer(this._userInput);
            if (isNegative){
                System.out.println("Please enter positive number for the answer.");
            }
        } while (isNegative);
    }

    private boolean checkUserAnswer(){
        int answer = Integer.parseInt(this._userInput);
        return answer == this._problem[3];
    }
    public void promptAnswerResult(){
        boolean correctAnswer = checkUserAnswer();
        if (correctAnswer){
            System.out.println("******           RIGHT!           ******");
        } else {
            System.out.println("******           WRONG!           ******");
        }
    }

    private boolean validateUserAnswer(String userInput){
        String pattern = "[0-9]";

        if (validate(pattern, userInput)){
            int intInput = Integer.parseInt(userInput);
            return intInput > 0;
        }

        return false;
    }
    private String generateLineDisplayCenter(String wordToDisplay){
        String output = "";
        int displayStart = (WIDTH / 2) - (wordToDisplay.length() / 2);
        for (int i = 0; i < WIDTH; i++) {
            if (isBlankWelcome(6, i)){
                if (i == displayStart) {
                    output += wordToDisplay;
                } else if (i > displayStart && i < wordToDisplay.length() + displayStart) {
                    output += "";
                } else {
                    output += " ";
                }
            } else {
                output += "*";
            }
        }
        return output;
    }
    public void displayStats(){
        String[] stringList = {"Name", "Total Earning", "Total Correct", "Total Incorrect"};

    }
    public void problemDisplay(){
        int userInput = Integer.parseInt(this._userInput);
        isProblemSelected(userInput);
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (isBlank(i) && j == 0 && i == 2) {
                    System.out.print(isProblemSelectedHeader(userInput));
                } else if (isBlank(i) && j == 0 && i == 6){
//                    FOR MATH PROBLEM
                    String output = problemSelectedLine(this._problem[1], this._problem[2], problemSelectedSign(userInput));
                    System.out.print(output);
                } else if (isBlank(i) && i != 2 && i != 6 && j == 0){
                    System.out.print("******                            ******");
                } else if (!isBlank(i)){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    /**
     * Outputs a String for math problem
     * ex. 12 + 12 = ?
     * @param a part A of the equation
     * @param b part B of the equation
     * @param sign sign of the equation
     * @return String of full equation except for answer
     */
    private String problemSelectedLine(int a, int b, String sign){
        String strA = String.valueOf(a);
        String strB = String.valueOf(b);
        String output = "";
        String problem = strA + " " + sign + " " + strB + " = ?";
        output = generateLineDisplayCenter(problem);
        return output;
    }
    private String problemSelectedSign(int mathProblemID){
        switch (mathProblemID){
            case 1 -> {return "+";}
            case 2 -> {return "-";}
            case 3 -> {return "*";}
            case 4 -> {return "/";}
            default -> {return null;}
        }
    }
    private void isProblemSelected(int mathProblemID){
        MathProblem mathProblem = new MathProblem();

        switch (mathProblemID) {
            case 1 -> this._problem =  mathProblem.generateAddition();
            case 2 -> this._problem =  mathProblem.generateSubtraction();
            case 3 -> this._problem =  mathProblem.generateMultiplication();
            case 4 -> this._problem = mathProblem.generateDivision();
        }
    }
    private String isProblemSelectedHeader(int iWhere){
        String[] headers = {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"};
        String msg = "";
        String output = "";
        switch (iWhere){
            case 1 -> msg = headers[0];
            case 2 -> msg = headers[1];
            case 3 -> msg = headers[2];
            case 4 -> msg = headers[3];
        }
        int header_start = (WIDTH / 2) - (msg.length() / 2);

        for (int i = 0; i < WIDTH; i++) {
            if (isBlankWelcome(2, i)){
                if (i == header_start){
                    output += msg;
                } else if (i > header_start && i < msg.length() + header_start) {
                    output += "";
                } else {
                    output += " ";
                }
            } else {
                output += "*";
            }
        }

        return output;
    }

    /**
     * Promps user to make selection to game.
     * If validated and correct, output the user input.
     */
    public void promptSelection(){
        boolean isInValidInput;
        do {
            Scanner scan = new Scanner(System.in);
            this._userInput = scan.nextLine();
            this._toContinue = this._userInput;
            isInValidInput = !validateSelectionInput(this._userInput);
            if (isInValidInput){
                System.out.println("Invalid input. Please try again.");
            }
        } while (isInValidInput);
    }

    private boolean isBlankWelcome(int x, int y){
        boolean boolInnerSquareWidth = y >= INNER_SQUARE_WIDTH_START && y < WIDTH - INNER_SQUARE_WIDTH_START;
        boolean boolInnerSquareLength = x >= INNER_SQUARE_LENGTH_START && x < LENGTH - INNER_SQUARE_LENGTH_START;
        return boolInnerSquareLength && boolInnerSquareWidth;
    }

    private boolean isBlank(int x){
        return x >= INNER_SQUARE_LENGTH_START && x < LENGTH - INNER_SQUARE_LENGTH_START;
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
    public boolean toContinue(){
        return !this._toContinue.equalsIgnoreCase("n");
    }
}
