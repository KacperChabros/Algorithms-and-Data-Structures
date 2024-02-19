package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

import org.junit.jupiter.api.Test;

public class RodCutterPerformanceTest {

    @Test
    public void rodCutterRecursivePerformanceTest() {

        RodCutter rodCutter = new RodCutterRecursive();
        int rodLength = 32;
        int[] prices = new int[rodLength];
        for (int i = 1; i <= rodLength; i++) {
            prices[i - 1] = i;
        }

        rodCutter.cutRod(prices, rodLength);
    }

    @Test
    public void rodCutterTopDownPerformanceTest() {

        RodCutter rodCutter = new RodCutterTopDown();
        int rodLength = 6500;
        int[] prices = new int[rodLength];
        for (int i = 1; i <= rodLength; i++) {
            prices[i - 1] = i;
        }

        rodCutter.cutRod(prices, rodLength);
    }

    @Test
    public void rodCutterBottomUpPerformanceTest() {

        RodCutter rodCutter = new RodCutterBottomUp();
        int rodLength = 130000;
        int[] prices = new int[rodLength];
        for (int i = 1; i <= rodLength; i++) {
            prices[i - 1] = i;
        }

        rodCutter.cutRod(prices, rodLength);
    }
}
