package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.Color;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.Node;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RbtMap;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RedBlackTree;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.AdvancedGetters.getRedBlackTree;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.AdvancedGetters.getRoot;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.NodeProviders.createNode;

public class HuffmanTreeTest {

    @Test
    public void should_ThrowException_WhenRootIsNull() {
        //given
        NodeHuffman root = null;

        String message = "Root can't be null!";
        String expectedMessage = createMessage(message, HuffmanTree.class.getName(),
                "HuffmanTree(NodeHuffman root)", IllegalArgumentException.class.getName());

        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            HuffmanTree tree = new HuffmanTree(root);
        });

        //then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_CorrectlyTraverse_WhenThereIsOneElement() {
        //given
        NodeHuffman root = new NodeHuffman((byte) 65, 100, null, null);
        HuffmanTree tree = new HuffmanTree(root);

        int expectedImportantBits = 1;
        List<Byte> expectedResult = new ArrayList(Arrays.asList((byte) -96, (byte) 1));

        //when
        TreePreorderResult result = tree.getPreorderResult();

        //then
        assertThat(result.getImportantBitsInTheLastByte()).isEqualTo(expectedImportantBits);
        assertThat(result.getTreePreorder()).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void should_CorrectlyCreateDictionaryForCompression_WhenThereIsOneElement() {
        //given
        NodeHuffman root = new NodeHuffman((byte) 65, 100, null, null);
        HuffmanTree tree = new HuffmanTree(root);
        Node<Byte, String> expectedResult = createNode((byte) 65, "1", Color.BLACK, null, null);

        //when
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        //then
        RedBlackTree<Byte, String> redBlackTree = (RedBlackTree<Byte, String>) getRedBlackTree(dictionary);
        Node<Byte, String> result = (Node<Byte, String>) getRoot(redBlackTree);

        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResult);
    }

    @Test
    public void should_CorrectlyCreateDictionaryForDecompression_WhenThereIsOneElement() {
        //given
        NodeHuffman root = new NodeHuffman((byte) 65, 100, null, null);
        HuffmanTree tree = new HuffmanTree(root);
        Node expectedResult = createNode("1", (byte) 65, Color.BLACK, null, null);

        //when
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        //then
        RedBlackTree<String, Byte> redBlackTree = (RedBlackTree<String, Byte>) getRedBlackTree(dictionary);
        Node<String, Byte> result = (Node<String, Byte>) getRoot(redBlackTree);

        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResult);
    }

    @Test
    public void should_CorrectlyTraverse_WhenThereAreManyElems() {
        //given
        NodeHuffman left = new NodeHuffman((byte) 78, 4, null, null);
        NodeHuffman rightRightRight = new NodeHuffman((byte) 76, 2, null, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) 75, 1, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 3, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 77, 3, null, null);
        NodeHuffman right = new NodeHuffman(null, 6, rightLeft, rightRight);
        NodeHuffman root = new NodeHuffman(null, 10, left, right);
        HuffmanTree tree = new HuffmanTree(root);

        int expectedImportantBits = 7;
        List<Byte> expectedResult = new ArrayList(Arrays.asList((byte) 83, (byte) -108, (byte) -43, (byte) 46, (byte) 76));

        //when
        TreePreorderResult result = tree.getPreorderResult();

        //then
        assertThat(result.getImportantBitsInTheLastByte()).isEqualTo(expectedImportantBits);
        assertThat(result.getTreePreorder()).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void should_CorrectlyTraverse_WhenWholeLastByteIsImportnat() {
        //given
        NodeHuffman rightRightRightLeft = new NodeHuffman((byte) 65, 1, null, null);
        NodeHuffman rightRightRight = new NodeHuffman(null, 1, rightRightRightLeft, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) 66, 2, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 3, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 67, 4, null, null);
        NodeHuffman right = new NodeHuffman(null, 7, rightLeft, rightRight);
        NodeHuffman left = new NodeHuffman((byte) 68, 8, null, null);
        NodeHuffman root = new NodeHuffman(null, 15, left, right);
        HuffmanTree tree = new HuffmanTree(root);

        int expectedImportantBits = 8;
        List<Byte> expectedResult = new ArrayList(Arrays.asList((byte) 81, (byte) 20, (byte) 53, (byte) 9, (byte) 65));

        //when
        TreePreorderResult result = tree.getPreorderResult();

        //then
        assertThat(result.getImportantBitsInTheLastByte()).isEqualTo(expectedImportantBits);
        assertThat(result.getTreePreorder()).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void should_CorrectlyTraverse_WhenByteShouldBeAddedRightAfterGettingNodeFromStack() {
        //given
        NodeHuffman rightRightRightLeft = new NodeHuffman((byte) 65, 1, null, null);
        NodeHuffman rightRightRightRight = new NodeHuffman((byte) 70, 1, null, null);
        NodeHuffman rightRightRight = new NodeHuffman(null, 1, rightRightRightLeft, rightRightRightRight);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) 66, 2, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 3, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 67, 4, null, null);
        NodeHuffman right = new NodeHuffman(null, 7, rightLeft, rightRight);
        NodeHuffman left = new NodeHuffman((byte) 68, 8, null, null);
        NodeHuffman root = new NodeHuffman(null, 15, left, right);
        HuffmanTree tree = new HuffmanTree(root);

        int expectedImportantBits = 1;
        List<Byte> expectedResult = new ArrayList(Arrays.asList((byte) 81, (byte) 20, (byte) 53, (byte) 9, (byte) 65, (byte) -93, (byte) 0));

        //when
        TreePreorderResult result = tree.getPreorderResult();

        //then
        assertThat(result.getImportantBitsInTheLastByte()).isEqualTo(expectedImportantBits);
        assertThat(result.getTreePreorder()).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void should_CorrectlyCreateDictionaryForCompression_WhenThereAreManyElems() {
        //given
        NodeHuffman left = new NodeHuffman((byte) 78, 4, null, null);
        NodeHuffman rightRightRight = new NodeHuffman((byte) 76, 2, null, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) 75, 1, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 3, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 77, 3, null, null);
        NodeHuffman right = new NodeHuffman(null, 6, rightLeft, rightRight);
        NodeHuffman root = new NodeHuffman(null, 10, left, right);
        HuffmanTree tree = new HuffmanTree(root);

        Node<Byte, String> leftOfLeft = createNode((byte) 75, "110", Color.RED, null, null);
        Node<Byte, String> leftChild = createNode((byte) 76, "111", Color.BLACK, leftOfLeft, null);
        Node<Byte, String> rightChild = createNode((byte) 78, "0", Color.BLACK, null, null);
        Node<Byte, String> expectedRoot = createNode((byte) 77, "10", Color.BLACK, leftChild, rightChild);

        //when
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();

        //then
        RedBlackTree<Byte, String> redBlackTree = (RedBlackTree<Byte, String>) getRedBlackTree(dictionary);
        Node<Byte, String> result = (Node<Byte, String>) getRoot(redBlackTree);

        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedRoot);
    }

    @Test
    public void should_CorrectlyCreateDictionaryForDecompression_WhenThereAreManyElems() {
        //given
        NodeHuffman left = new NodeHuffman((byte) 78, 4, null, null);
        NodeHuffman rightRightRight = new NodeHuffman((byte) 76, 2, null, null);
        NodeHuffman rightRightLeft = new NodeHuffman((byte) 75, 1, null, null);
        NodeHuffman rightRight = new NodeHuffman(null, 3, rightRightLeft, rightRightRight);
        NodeHuffman rightLeft = new NodeHuffman((byte) 77, 3, null, null);
        NodeHuffman right = new NodeHuffman(null, 6, rightLeft, rightRight);
        NodeHuffman root = new NodeHuffman(null, 10, left, right);
        HuffmanTree tree = new HuffmanTree(root);

        Node<String, Byte> leftOfRight = createNode("110", (byte) 75, Color.RED, null, null);
        Node<String, Byte> rightChild = createNode("111", (byte) 76, Color.BLACK, leftOfRight, null);
        Node<String, Byte> leftChild = createNode("0", (byte) 78, Color.BLACK, null, null);
        Node<String, Byte> expectedRoot = createNode("10", (byte) 77, Color.BLACK, leftChild, rightChild);

        //when
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();

        //then
        RedBlackTree<String, Byte> redBlackTree = (RedBlackTree<String, Byte>) getRedBlackTree(dictionary);
        Node<String, Byte> result = (Node<String, Byte>) getRoot(redBlackTree);

        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedRoot);
    }

}
