import java.util.Random;

public class MathProblem {
    private Random _rand = new Random();
    private final int[] NUMBER_LIST = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public MathProblem(){

    }

    /**
     * Utilize pre-generated list to output a random number
     * @return int
     */
    private int generateRandNum(){
        int randIndex = _rand.nextInt(NUMBER_LIST.length);
        return NUMBER_LIST[randIndex];
    }
    /**
     * The generateAddition method creates a basic Addition problem
     * using pre-generated list of numbers
     * Outputs as Int array with 1 as identifier on index 0
     * @return Int Array
     */
    public int[] generateAddition(){
        int questionA, questionB, answer;
        questionA = generateRandNum();
        questionB = generateRandNum();
        answer = questionA + questionB;
        return new int[]{1, questionA, questionB, answer};
    }
    /**
     * The generateSubtraction method creates a basic Subtraction problem
     * using pre-generated list of numbers
     * If result of generated problem is less than 0, will generate again.
     * Outputs as Int array with 2 as identifier on index 0
     * @return Int Array
     */
    public int[] generateSubtraction(){
        int questionA, questionB, answer;
        questionA = generateRandNum();
        do {
            questionB = generateRandNum();
        } while (questionA < questionB);

        answer = questionA - questionB;
        return new int[]{2, questionA, questionB, answer};
    }
    /**
     * The generateMultiplication method creates a basic Multiplication problem
     * using pre-generated list of numbers
     * Will generate again for the problem is 0 * 0
     * Outputs as Int array with 3 as identifier on index 0
     * @return Int Array
     */
    public int[] generateMultiplication(){
        int questionA, questionB, answer;
        questionA = generateRandNum();
        do {
            questionB = generateRandNum();
        } while (questionB == 0 && questionA == 0);

        answer = questionA * questionB;
        return new int[]{3, questionA, questionB, answer};
    }
    /**
     * The generateDivision method creates a basic Division problem
     * using pre-generated list of numbers
     * Will generate again if denominator is 0
     * Outputs as Int array with 4 as identifier on index 0
     * @return Int Array
     */
    public int[] generateDivision(){
        int questionA, questionB, answer;
        answer = generateRandNum();
        do {
            questionB = generateRandNum();
        } while (questionB == 0);

        questionA = answer * questionB;
        return new int[]{4, questionA, questionB, answer};
    }
}
