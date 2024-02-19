package pl.edu.pw.ee.aisd2023zlab6.matrixchainorder;

import static java.util.Objects.isNull;

public class MatrixChainOrderBottomUp {

    public MatrixChainOrderExtendedResult findOptimalOrderCost(int[] matrixSizes) {
        validateInput(matrixSizes);

        int numOfMatrices = matrixSizes.length;

        int[][] costCounters = new int[numOfMatrices][numOfMatrices];
        int[][] solutions = new int[numOfMatrices - 1][numOfMatrices];
        int indexOfLastChain, j;
        int calculationCost, currentMultiplyCost, subMatricesCost;

        for (int nOfMatricesInChain = 2; nOfMatricesInChain <= numOfMatrices; nOfMatricesInChain++) {

            indexOfLastChain = numOfMatrices - nOfMatricesInChain + 1;

            for (int i = 1; i < indexOfLastChain; i++) {

                j = i + nOfMatricesInChain - 1;
                costCounters[i][j] = Integer.MAX_VALUE;

                for (int pivotIndex = i; pivotIndex < j; pivotIndex++) {

                    subMatricesCost = costCounters[i][pivotIndex] + costCounters[pivotIndex + 1][j];
                    currentMultiplyCost = matrixSizes[i - 1] * matrixSizes[pivotIndex] * matrixSizes[j];
                    calculationCost = subMatricesCost + currentMultiplyCost;

                    if (calculationCost < costCounters[i][j]) {
                        costCounters[i][j] = calculationCost;
                        solutions[i][j] = pivotIndex;
                    }
                }
            }

        }
        
        int minMultiplyCost = costCounters[1][numOfMatrices - 1];
        MatrixChainOrderExtendedResult finalResult = new MatrixChainOrderExtendedResult(minMultiplyCost, solutions);

        return finalResult;
    }

    private void validateInput(int[] matrixSizes) {
        if (isNull(matrixSizes)) {
            throw new IllegalArgumentException("The matrixSizes cannot be null!");
        }
        if (matrixSizes.length < 2) {
            throw new IllegalArgumentException("The matrixSizes must contain at least two values!");
        }
    }

}
