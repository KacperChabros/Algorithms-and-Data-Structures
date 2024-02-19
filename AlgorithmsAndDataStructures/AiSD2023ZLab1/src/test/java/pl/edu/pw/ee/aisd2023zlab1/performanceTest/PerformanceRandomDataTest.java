package pl.edu.pw.ee.aisd2023zlab1.performanceTest;

import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateRandomNums;

public class PerformanceRandomDataTest extends PerformanceTest{

    @Override
    protected double[] generateNums(int size) {
        return generateRandomNums(size);
    }
    
}
