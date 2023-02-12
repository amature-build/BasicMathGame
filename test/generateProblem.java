import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class generateProblem {
    private final int ID_ADDITION = 1;
    private final int ID_SUBTRACTION = 2;
    private final int ID_MULTIPLICATION = 3;
    private final int ID_DIVISION = 4;
    MathProblem test = new MathProblem();

    @Test
    public void additionIdentifier(){
        int[] problem = test.generateAddition();
        Assertions.assertEquals(problem[0], ID_ADDITION);
    }
    @Test
    public void additionAnswer(){
        int[] problem = test.generateAddition();
        int answer = problem[1] + problem[2];
        Assertions.assertEquals(problem[3], answer);
    }
    @Test
    public void additionGreaterThanZero(){
        int[] problem = test.generateAddition();
        Assertions.assertTrue(problem[3] > 0);
    }
    @Test
    public void subtractionIdentifier(){
        int[] problem = test.generateSubtraction();
        Assertions.assertEquals(problem[0], ID_SUBTRACTION);
    }
    @Test
    public void subtractionAnswer(){
        int[] problem = test.generateSubtraction();
        int answer = problem[1] - problem[2];
        Assertions.assertEquals(problem[3], answer);
    }
    @Test
    public void subtractionAnswerPositiveInteger(){
        int[] problem = test.generateSubtraction();
        Assertions.assertTrue(problem[3] >= 0);
    }
    @Test
    public void multiplicationIdentifier(){
        int[] problem = test.generateMultiplication();
        Assertions.assertEquals(problem[0], ID_MULTIPLICATION);
    }
    @Test
    public void multiplicationAnswer(){
        int[] problem = test.generateMultiplication();
        int answer = problem[1] * problem[2];
        Assertions.assertEquals(problem[3], answer);
    }
    @Test
    public void multiplicationBothPartOfQuestionNotZero(){
        int[] problem = test.generateMultiplication();
        Assertions.assertTrue(problem[1] != problem[2]);
    }
    @Test
    public void divisionIdentifier(){
        int[] problem = test.generateDivision();
        Assertions.assertEquals(problem[0], ID_DIVISION);
    }
    @Test
    public void divisionNotDivideByZero(){
        int[] problem = test.generateDivision();
        int denominator = problem[2];
        Assertions.assertTrue(denominator != 0);
    }
    @Test
    public void divisionWorksAsIntended(){
        int[] problem = test.generateDivision();
        boolean testCase1 = problem[1] >= problem[2];
        boolean testCase2 = problem[3] >= 0;
        boolean testCase3 = problem[1] % problem[2] != 1;
        Assertions.assertTrue(testCase1 && testCase2 && testCase3);
    }
}
