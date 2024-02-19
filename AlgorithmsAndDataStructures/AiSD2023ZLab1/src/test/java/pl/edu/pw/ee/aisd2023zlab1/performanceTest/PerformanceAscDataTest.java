package pl.edu.pw.ee.aisd2023zlab1.performanceTest;

import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateAscData;

public class PerformanceAscDataTest extends PerformanceTest {

    @Override
    protected double[] generateNums(int size) {
        return generateAscData(size);
    }

}

