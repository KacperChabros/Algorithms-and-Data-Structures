package pl.edu.pw.ee.aisd2023zlab5.utils.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;

public class ParameterValidatorTest {

    @Test
    public void should_ThrowException_WhenArgsIsNull() {
        //given
        String args[] = null;

        String message = "FilePath, OutfilePath and mode need to be provided!";
        String method = "validateAndChooseMode(String[] args)";
        String expectedMessage = createMessage(message, ParametersValidator.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenArgsLengthIs0() {
        //given
        String args[] = new String[0];

        String message = "FilePath, OutfilePath and mode need to be provided!";
        String method = "validateAndChooseMode(String[] args)";
        String expectedMessage = createMessage(message, ParametersValidator.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenThereIs1Element() {
        //given
        String args[] = new String[]{"Test"};

        String message = "All three parameters need to be provided!";
        String method = "validateAndChooseMode(String[] args)";
        String expectedMessage = createMessage(message, ParametersValidator.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheParameterIsBlank() {
        //given
        String args[] = new String[]{"Test", "  \t \n", ""};

        String message = "The parameters cannot be blank!";
        String method = "validateAndChooseMode(String[] args)";
        String expectedMessage = createMessage(message, ParametersValidator.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheThirdParameterIsNotValid() {
        //given
        String args[] = new String[]{"Test", "Test2", "X"};

        String message = "Wrong mode provided! Use C for compression or D for decompression";
        String method = "shouldCompress(String argument)";
        String expectedMessage = createMessage(message, ParametersValidator.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_NotThrowException_WhenTheThirdIsLowerCase() {
        //given
        String args[] = new String[]{"Test", "Test2", "c"};

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught).isNull();
    }

    @Test
    public void should_NotThrowException_WhenTheThirdIsUpperCase() {
        //given
        String args[] = new String[]{"Test", "Test2", "D"};

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            ParametersValidator.validateInputAndChooseMode(args);
        });

        //then
        assertThat(exceptionCaught).isNull();
    }
}
