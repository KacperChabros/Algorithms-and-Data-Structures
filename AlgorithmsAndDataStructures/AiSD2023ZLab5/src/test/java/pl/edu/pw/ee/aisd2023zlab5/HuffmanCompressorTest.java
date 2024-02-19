package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import pl.edu.pw.ee.aisd2023zlab5.testutils.FileRemover;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;

public class HuffmanCompressorTest {

    private final String directory = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\testResources\\";

    @Test
    public void should_ThrowException_WhenThePathIsWhiteSpace() {
        //given
        String filePath = "  \n \t ";
        String outfilePath = "out.txt";

        String message = "filePath and outfilePath can't be null or whitespace";
        String method = "validatePaths(String filePath, String outfilePath)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.compress(filePath, outfilePath);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheOutFilePathIsWhiteSpace() {
        //given
        String filePath = "aaaa";
        String outfilePath = "";

        String message = "filePath and outfilePath can't be null or whitespace";
        String method = "validatePaths(String filePath, String outfilePath)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.compress(filePath, outfilePath);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenFileToCompressIsEmpty() {
        //given
        String sourceFile = "empty.txt";
        String destinationFile = "empty2.txt";

        String message = "Cannot compress an empty file";
        String method = "validateFileForCompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.compress(directory + sourceFile, directory + destinationFile);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    public void should_CorrectlyCompress_WhenThereIsOneSymbolInTheFile() throws IOException {
        //given
        String sourceFile = "oneSymbolToCompress.txt";
        String destinationFile = "oneSymbolToCompressResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{88, 65, 18, 8, -96, -1, -1};

        //when
        HuffmanCompressor.compress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyDeCompress_WhenThereIsOneSymbolInTheFile() throws IOException {
        //given
        String sourceFile = "oneSymbolToDecompress.txt";
        String destinationFile = "oneSymbolToDecompressResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65};

        //when
        HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyCompress_WhenThereAreManySymbols() throws IOException {
        //given
        String sourceFile = "wholeLastByteOfDictionaryIsImportant.txt";
        String destinationFile = "wholeLastByteOfDictionaryIsImportantResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{88, 65, -116, 8, 20, 26, 21, 14, -120, 9, 85, -1};

        //when
        HuffmanCompressor.compress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyDeCompress_WhenThereAreManySymbols() throws IOException {
        //given
        String sourceFile = "wholeLastByteOfDictionaryIsImportantToDecompress.txt";
        String destinationFile = "wholeLastByteOfDictionaryIsImportantToDecompressResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{65, 66, 66, 67, 67, 67, 67, 68, 68, 68, 68, 68, 68, 68, 68};

        //when
        HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyCompress_WhenThereAreNegatives() throws IOException {
        //given
        String sourceFile = "fileWithNegatives.txt";
        String destinationFile = "fileWithNegativesResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{88, 65, 116, 1, 32, 27, 85, -111, -100, -101, -106, 122, 91, -128};

        //when
        HuffmanCompressor.compress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyDecompress_WhenThereAreNegatives() throws IOException {
        //given
        String sourceFile = "compressedWithNegativesToDecompress.txt";
        String destinationFile = "compressedWithNegativesToDecompressResult.txt";

        //expected
        byte[] expectedBytes = new byte[]{0, 100, -100, -75, 55, -75, 0, 100, -100, 55};

        //when
        HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + destinationFile));
        FileRemover.removeFile(directory + destinationFile);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_ThrowException_WhenFileToDecompressDoesntHaveProperHeaderWithX() {
        //given
        String sourceFile = "fileWithoutX.txt";
        String destinationFile = "fileWithoutXResult.txt";

        String message = "This file hasn't been compressed by this compressor! (or is corrupted)";
        String method = "validateFileForDecompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenFileToDecompressDoesntHaveProperHeaderWithA() {
        //given
        String sourceFile = "fileWithoutA.txt";
        String destinationFile = "fileWithoutAResult.txt";

        String message = "This file hasn't been compressed by this compressor! (or is corrupted)";
        String method = "validateFileForDecompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenFileToDecompressIsShorterThan5Bytes() {
        //given
        String sourceFile = "tooShortToDecompress.txt";
        String destinationFile = "tooShortToDecompressResult.txt";

        String message = "The file is too short to have been compressed by this compressor";
        String method = "validateFileForDecompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenFileHasBrokenTree() {
        //given
        String sourceFile = "fileWithBrokenTree.txt";
        String destinationFile = "fileWithBrokenTreeResult.txt";

        String message = "This file is corrupted!";
        String method = "validateFileForDecompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);
        });
        //then
        assertThat(exceptionCaught
        )
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenFileHasReplacedSymbol() {
        //given
        String sourceFile = "fileWithReplacedSymbol.txt";
        String destinationFile = "fileWithReplacedSymbolResult.txt";

        String message = "This file is corrupted!";
        String method = "validateFileForDecompression(byte[] fileAsBytes)";
        String expectedMessage = createMessage(message, HuffmanCompressor.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanCompressor.decompress(directory + sourceFile, directory + destinationFile);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

}
