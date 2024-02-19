package pl.edu.pw.ee.aisd2023zlab6.matrixchainorder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixChainOrderResultTest {

    private MatrixChainOrderBottomUp matrixChain;

    @BeforeEach
    public void setup() {
        matrixChain = new MatrixChainOrderBottomUp();
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        int[] matrixSizes = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrderCost(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes cannot be null!");
    }

    @Test
    public void should_ThrowException_When_InputSizeIsLessThanTwo() {
        // given
        int[] matrixSizes = {7};

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrderCost(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes must contain at least two values!");
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @Test
    public void should_ReturnCorrectResult_When_CorrectSmallInput() {
        // given
        int[] matrixSizes = {30, 35, 15, 5, 10, 20, 25};

        // when
        MatrixChainOrderExtendedResult result = matrixChain.findOptimalOrderCost(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 15125;
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }

}
