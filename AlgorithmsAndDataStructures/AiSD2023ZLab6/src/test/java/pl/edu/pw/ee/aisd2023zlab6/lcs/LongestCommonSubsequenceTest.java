package pl.edu.pw.ee.aisd2023zlab6.lcs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test
    public void should_ThrowException_WhenParameterIsNull() {
        //given
        String topText = null;
        String leftText = null;
        String expectedMessage = "Parameters cannot be null!";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            String lcsResult = lcs.findLcs(topText, leftText);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ReturnEmptyString_WhenThereIsNoSubsequence() {
        //given
        String topText = "mleko";
        String leftText = "cis";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String expectedText = "";

        //when
        String lcsResult = lcs.findLcs(topText, leftText);

        //then
        assertThat(lcsResult).isEqualTo(expectedText);
    }

    @Test
    public void should_ReturnCorrectString_WhenThereIsSubsequence() {
        //given
        String topText = "PIEROGI";
        String leftText = "WIROG";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String expectedText = "IROG";

        //when
        String lcsResult = lcs.findLcs(topText, leftText);

        //then
        assertThat(lcsResult).isEqualTo(expectedText);
    }

    @Test
    public void should_ReturnCorrectString_WhenSubsequenceIsOneWholeString() {
        //given
        String topText = "KAJAKOWY";
        String leftText = "KAJMAKOWY";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String expectedText = "KAJAKOWY";

        //when
        String lcsResult = lcs.findLcs(topText, leftText);

        //then
        assertThat(lcsResult).isEqualTo(expectedText);
    }
}
