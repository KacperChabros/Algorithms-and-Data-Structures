package pl.edu.pw.ee.aisd2023zlab5.utils.filehandling;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;

public class FileReaderTest {

    private final String directory = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\testResources\\";

    @Test
    public void should_ThrowException_WhenFileDoesntExist() {
        //given
        String name = "nonexisting.txt";

        String message = "This file doesn't exist!";
        String method = "getFileAsBytes(String filePath)";
        String expectedMessage = createMessage(message, FileReader.class.getName(),
                method, IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            byte[] fileAsBytes = FileReader.getFileAsBytes(directory + name);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ReturnEmptyArray_WhenFileIsEmpty() {
        //given
        String name = "empty.txt";

        //when
        byte[] fileAsBytes = FileReader.getFileAsBytes(directory + name);

        //then
        assertThat(fileAsBytes).isEmpty();
    }

    @Test
    public void should_ReturnCorrectArray_WhenFileIsNotEmpty() {
        //given
        String name = "charactersAndWhitespace.txt";

        String givenString = " this are characters, numbers\t142 and\r\nwhitespace!";
        byte[] expected = givenString.getBytes();

        //when
        byte[] fileAsBytes = FileReader.getFileAsBytes(directory + name);

        //then
        assertThat(fileAsBytes).containsExactly(expected);
    }
}
