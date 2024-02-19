package pl.edu.pw.ee.aisd2023zlab5.utils.filehandling;

import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.HuffmanTree;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.TreePreorderResult;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.RecreatedTreeResult;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.NodeHuffman;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RbtMap;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;
import pl.edu.pw.ee.aisd2023zlab5.testutils.FileRemover;

public class FileWriterTest {

    private final String directory = "src\\test\\java\\pl\\edu\\pw\\ee\\aisd2023zlab5\\testResources\\";

    @Test
    public void should_ThrowException_WhenTheFileAlreadyExistsWhileCompressing() {
        //given
        String outfilePath = "existing.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "The file with the given name already exists!";
        String method = "writeCodesToFile(String outfilePath, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(),
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFileAlreadyExistsWhileDecompressing() {
        //given
        String outfilePath = "existing.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        RecreatedTreeResult recreatedTree = new RecreatedTreeResult(tree, 0, 0);
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        String message = "The file with the given name already exists!";
        String method = "writeDecodedFile(String outfilePath, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(),
                method, IllegalArgumentException.class.getName());
        
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeDecodedFile(directory + outfilePath, fileAsBytes, recreatedTree, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFilePathIsNull() {
        //given
        String outfilePath = null;
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "FilePath can't be null nor whitespace";
        String method= "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFilePathIsBlank() {
        //given
        String outfilePath = "   \t \n ";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "FilePath can't be null nor whitespace";
        String method = "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFileAsBytesIsNull() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = null;
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "fileAsBytes can't be null nor empty";
        String method = "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFileAsBytesIsEmpty() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = new byte[0];
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "fileAsBytes can't be null nor empty";
        String method = "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheFileDictionaryIsNull() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = tree.getPreorderResult();
        RbtMap<Byte, String> dictionary = null;

        String message = "dictionary can't be null";
        String method = "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheTreePreorderIsNull() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        TreePreorderResult treePreorder = null;
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        String message = "preorder can't be null nor empty";
        String method = "validatePreorder(TreePreorderResult preorder)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, treePreorder, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheRecreatedTreeIsNull() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = new HuffmanTree(new NodeHuffman((byte) 5, 5, null, null));
        RecreatedTreeResult recreatedTreeResult = null;
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        String message = "recreatedTree can't be null nor empty";
        String method = "validateRecreatedTree(RecreatedTreeResult recreatedTree)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeDecodedFile(directory + outfilePath, fileAsBytes, recreatedTreeResult, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenTheRecreatedTreeIsEmpty() {
        //given
        String outfilePath = "aa.txt";
        byte[] fileAsBytes = new byte[]{5};
        HuffmanTree tree = null;
        RecreatedTreeResult recreatedTreeResult = new RecreatedTreeResult(tree, 0, 0);

        RbtMap<String, Byte> dictionary = new RbtMap<>();
        dictionary.setValue("a", (byte) 5);

        String message = "recreatedTree can't be null nor empty";
        String method = "validateRecreatedTree(RecreatedTreeResult recreatedTree)";
        String expectedMessage = createMessage(message, FileWriter.class.getName(), 
                method, IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            FileWriter.writeDecodedFile(directory + outfilePath, fileAsBytes, recreatedTreeResult, dictionary);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_CorrectlyCompressFile_WhenThereAreNegativeValues() throws IOException {
        //given
        NodeHuffman leftLeft = new NodeHuffman((byte) 100, 2, null, null);
        NodeHuffman leftRight = new NodeHuffman((byte) -100, 2, null, null);
        NodeHuffman left = new NodeHuffman(null, 4, leftLeft, leftRight);
        NodeHuffman rightRightRight = new NodeHuffman((byte) 0, 2, null, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) -75, 2, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 4, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 55, 2, null, null);
        NodeHuffman right = new NodeHuffman(null, 6, rightLeft, rightRight);
        NodeHuffman root = new NodeHuffman(null, 10, left, right);

        String outfilePath = "compressedWithNegatives.txt";
        byte[] fileAsBytes = new byte[]{0, 100, -100, -75, 55, -75, 0, 100, -100, 55};
        HuffmanTree tree = new HuffmanTree(root);
        TreePreorderResult preorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        //expected
        byte[] expectedBytes = new byte[]{88, 65, 102, 1, 44, -103, -60, -35, -75, -128, 113, -42, -29, 0};

        //when
        FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, preorder, dictionary);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + outfilePath));
        FileRemover.removeFile(directory + outfilePath);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyCompress_WhenThereIsOneSymbol() throws IOException {
        //given
        String outfilePath = "oneSymbol.txt";
        byte[] fileAsBytes = new byte[]{5, 5, 5, 5, 5, 5, 5};
        NodeHuffman root = new NodeHuffman((byte) 5, 7, null, null);
        HuffmanTree tree = new HuffmanTree(root);
        TreePreorderResult preorder = tree.getPreorderResult();
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        //expected
        byte[] expectedBytes = new byte[]{88, 65, -49, 8, -126, -1};

        //when
        FileWriter.writeCodesToFile(directory + outfilePath, fileAsBytes, preorder, dictionary);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + outfilePath));
        FileRemover.removeFile(directory + outfilePath);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }

    @Test
    public void should_CorrectlyDecompress_WhenThereIsOneSymbol() throws IOException {
        //given
        String outfilePath = "oneSymbolDecompressed.txt";
        byte[] fileAsBytes = new byte[]{88, 65, -49, 8, -126, -1};
        NodeHuffman root = new NodeHuffman((byte) 5, 7, null, null);
        HuffmanTree tree = new HuffmanTree(root);
        
        RecreatedTreeResult recreatedTree = new RecreatedTreeResult(tree, 5, 7);
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        //expected
        byte[] expectedBytes = new byte[]{5,5,5,5,5,5,5};

        //when
        FileWriter.writeDecodedFile(directory + outfilePath, fileAsBytes, recreatedTree, dictionary);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + outfilePath));
        FileRemover.removeFile(directory + outfilePath);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }
    @Test
    public void should_CorrectlyDecompressFile_WhenThereAreNegativeValues() throws IOException {
        //given
        NodeHuffman leftLeft = new NodeHuffman((byte) 100, 2, null, null);
        NodeHuffman leftRight = new NodeHuffman((byte) -100, 2, null, null);
        NodeHuffman left = new NodeHuffman(null, 4, leftLeft, leftRight);
        NodeHuffman rightRightRight = new NodeHuffman((byte) 0, 2, null, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) -75, 2, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 4, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 55, 2, null, null);
        NodeHuffman right = new NodeHuffman(null, 6, rightLeft, rightRight);
        NodeHuffman root = new NodeHuffman(null, 10, left, right);

        String outfilePath = "decompressedWithNegatives.txt";
        byte[] fileAsBytes = new byte[]{88, 65, 102, 1, 44, -103, -60, -35, -75, -128, 113, -42, -29, 0};
        HuffmanTree tree = new HuffmanTree(root);
        RecreatedTreeResult recreatedTree = new RecreatedTreeResult(tree, 10, 7);
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        //expected
        byte[] expectedBytes = new byte[]{0, 100, -100, -75, 55, -75, 0, 100, -100, 55};

        //when
        FileWriter.writeDecodedFile(directory + outfilePath, fileAsBytes, recreatedTree, dictionary);

        //then
        byte[] result = Files.readAllBytes(Paths.get(directory + outfilePath));
        FileRemover.removeFile(directory + outfilePath);

        assertThat(result)
                .hasSize(expectedBytes.length)
                .containsExactly(expectedBytes);
    }
    
}
