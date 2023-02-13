public class Main {
    public static void main(String[] args) {

        GameUI ui = new GameUI();

        ui.welcome();
        ui.promptUserName();

        ui.problemSelection();

        ui.promptSelection();
        ui.problemDisplay();
        ui.promptSolution();
        ui.promptAnswerResult();

    }

}