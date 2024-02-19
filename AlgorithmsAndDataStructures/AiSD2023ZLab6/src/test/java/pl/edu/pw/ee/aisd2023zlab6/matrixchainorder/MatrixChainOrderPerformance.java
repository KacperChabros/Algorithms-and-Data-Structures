package pl.edu.pw.ee.aisd2023zlab6.matrixchainorder;

import org.junit.jupiter.api.Test;

public class MatrixChainOrderPerformance {

    @Test
    public void should_CorrectlySolve_ProblemFromCormen() {
        MatrixChainOrderRecursive matrixOrder = new MatrixChainOrderRecursive();
        int[] matrixSizes = new int[] {30,35,15,5,10,20,25};

        MatrixChainOrderResult result = matrixOrder.findOptimalOrder(matrixSizes);

        System.out.println(result.getMinMultiplyCost());
    }

    @Test
    public void performanceTest() {
        MatrixChainOrderBottomUp matrixOrder = new MatrixChainOrderBottomUp();
        int sizeOfMatrixSizes = 2000;
        int[] matrixSizes = new int[sizeOfMatrixSizes];
        for (int i = 0; i < sizeOfMatrixSizes; i++) {
            matrixSizes[i] = i + 100;
        }
        MatrixChainOrderResult result = matrixOrder.findOptimalOrderCost(matrixSizes);

    }
}
