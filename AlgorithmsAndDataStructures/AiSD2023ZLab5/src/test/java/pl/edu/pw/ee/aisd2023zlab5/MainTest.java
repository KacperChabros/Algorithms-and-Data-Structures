package pl.edu.pw.ee.aisd2023zlab5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.assertj.core.api.Assertions.catchThrowable;
import pl.edu.pw.ee.aisd2023zlab5.testutils.FileRemover;

public class MainTest {

    private final String directory = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\testResources\\";

    @Test
    public void should_CreateFileWithTheSameContent_WhenItWasCompressedAndDecompressed() throws IOException {
        //given
        String[] argsToCompress = new String[3];
        String[] argsToDecompress = new String[3];
        String fileName = "loremIpsum.txt";
        String fileCompressedName = "loremIpsumCompressed.txt";
        String fileDecompressedName = "loremIpsumDecompressed.txt";
        argsToCompress[0] = directory + fileName;
        argsToCompress[1] = directory + fileCompressedName;
        argsToCompress[2] = "C";

        argsToDecompress[0] = directory + fileCompressedName;
        argsToDecompress[1] = directory + fileDecompressedName;
        argsToDecompress[2] = "D";

        byte[] expectedBytes = Files.readAllBytes(Paths.get(argsToCompress[0]));

        //when
        Main.main(argsToCompress);
        Main.main(argsToDecompress);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + fileDecompressedName));
        FileRemover.removeFile(directory + fileCompressedName);
        FileRemover.removeFile(directory + fileDecompressedName);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(result);
    }

    @Test
    public void should_NotCrash_WhenWrongParametersWerePassed() {
        String[] args = new String[0];

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            Main.main(args);
        });

        //then
        assertThat(exceptionCaught).isNull();
    }

    @Test
    public void should_NotCrash_WhenWrongPathWasPassed() {
        String[] args = new String[]{"wrongpath", "path2", "C"};

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            Main.main(args);
        });

        //then
        assertThat(exceptionCaught).isNull();
    }
}
