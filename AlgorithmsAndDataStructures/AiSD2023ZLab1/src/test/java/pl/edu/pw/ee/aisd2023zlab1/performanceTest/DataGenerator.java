package pl.edu.pw.ee.aisd2023zlab1.performanceTest;

import java.util.Random;

public class DataGenerator {

    private static final Random RAND;

    static {
        int seed = 42;
        RAND = new Random(seed);
    }

    public static double[] generateRandomNums(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        for (int i = 0; i < size; i++) {
            nums[i] = RAND.nextDouble();
        }

        return nums;
    }
    
    public static double[] generateAscData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }

        return nums;
    }
}
