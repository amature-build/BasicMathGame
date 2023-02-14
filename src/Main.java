public class Main {
    public static void main(String[] args) {

        GameUI ui = new GameUI();

        ui.welcome();
        ui.promptUserName();
        ui.problemSelection();
        ui.promptSelection();
        do {
            ui.problemDisplay();
            ui.promptSolution();
            ui.promptAnswerResult();
            ui.problemSelection();
            ui.promptSelection();
        } while (ui.toContinue());

    }

}