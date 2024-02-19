package pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.AdvancedGetters.getRedBlackTree;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.AdvancedGetters.getRoot;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.NodeProviders.createNode;

public class RbtMapTest {

    @Test
    public void should_ThrowException_WhenPutKeyIsNull() {
        //given
        Integer keyToPut = null;
        String valueToPut = "put";
        RbtMap<Integer, String> rbtMap = new RbtMap<>();

        String message = "Params (key, value) cannot be null.";
        String expectedMessage = createMessage(message, RbtMap.class.getName(),
                "setValue(K key, V value)", IllegalArgumentException.class.getName());

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbtMap.setValue(keyToPut, valueToPut);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenPutValueIsNull() {
        //given
        Integer keyToPut = 5;
        String valueToPut = null;
        RbtMap<Integer, String> rbtMap = new RbtMap<>();

        String message = "Params (key, value) cannot be null.";
        String expectedMessage = createMessage(message, RbtMap.class.getName(),
                "setValue(K key, V value)", IllegalArgumentException.class.getName());

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbtMap.setValue(keyToPut, valueToPut);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_CorrectlyAddElement_WhenThereIsNoInTheTable() {
        //given
        Integer[] keysToPut = {116};
        String[] valuesToPut = {"cat"};
        RbtMap<Integer, String> rbtmap = new RbtMap<>();

        Node<Integer, String> expectedTree = createNode(116, "cat", Color.BLACK, null, null);
        //when
        for (int i = 0; i < keysToPut.length; i++) {
            rbtmap.setValue(keysToPut[i], valuesToPut[i]);
        }

        //then
        RedBlackTree<Integer, String> tree = (RedBlackTree<Integer, String>) getRedBlackTree(rbtmap);
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }

    @Test
    public void should_CorrectlyReplaceElement_WhenItIsPresentInTheTable() {
        //given
        Integer[] keysToPut = {116, 116};
        String[] valuesToPut = {"cat", "dog"};
        RbtMap<Integer, String> rbtmap = new RbtMap<>();

        Node<Integer, String> expectedTree = createNode(116, "dog", Color.BLACK, null, null);
        //when
        for (int i = 0; i < keysToPut.length; i++) {
            rbtmap.setValue(keysToPut[i], valuesToPut[i]);
        }

        //then
        RedBlackTree<Integer, String> tree = (RedBlackTree<Integer, String>) getRedBlackTree(rbtmap);
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }

    @Test
    public void should_CorrectlyAddManyElements_WhenThereIsEveryFixupScenario() {
        //given
        Integer[] keysToPut = {116, 100, 160, 90, 115};
        String[] valuesToPut = {"cat", "dog", "lizard", "squirrel", "fox"};
        RbtMap<Integer, String> rbtmap = new RbtMap<>();

        Node<Integer, String> leftChildOfLeft = createNode(90, "squirrel", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfLeft = createNode(115, "fox", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(100, "dog", Color.RED, leftChildOfLeft, rightChildOfLeft);
        Node<Integer, String> rightChild = createNode(160, "lizard", Color.BLACK, null, null);
        Node<Integer, String> expectedTree = createNode(116, "cat", Color.BLACK, leftChild, rightChild);
        //when
        for (int i = 0; i < keysToPut.length; i++) {
            rbtmap.setValue(keysToPut[i], valuesToPut[i]);
        }

        //then
        RedBlackTree<Integer, String> tree = (RedBlackTree<Integer, String>) getRedBlackTree(rbtmap);
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }

    @Test
    public void should_ThrowException_WhenGetKeyIsNull() {
        //given
        Integer keyToGet = null;
        RbtMap<Integer, String> rbtMap = new RbtMap<>();

        String message = "Cannot get value by null key.";
        String expectedMessage = createMessage(message, RbtMap.class.getName(),
                "getValue(K key)", IllegalArgumentException.class.getName());

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rbtMap.getValue(keyToGet);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ReturnNull_WhenTheMapIsEmpty() {
        //given
        RbtMap<Integer, String> rbtMap = new RbtMap<>();
        Integer keyToGet = 500;

        //when
        String result = rbtMap.getValue(keyToGet);

        //then
        assertThat(result).isNull();
    }

    @Test
    public void should_ReturnNull_WhenTheKeyDoesntExist() {
        //given
        RbtMap<Integer, String> rbtMap = new RbtMap<>();
        Integer keyToPut = 600;
        String valueToPut = "lalala";
        Integer keyToGet = 500;

        rbtMap.setValue(keyToPut, valueToPut);

        //when
        String result = rbtMap.getValue(keyToGet);

        //then
        assertThat(result).isNull();
    }

    @Test
    public void should_ReturnCorrectValue_WhenTheKeyIsPresent() {
        //given
        RbtMap<Integer, String> rbtMap = new RbtMap<>();
        Integer keyToGet = 600;
        String valueToPut = "lalala";

        rbtMap.setValue(keyToGet, valueToPut);

        //when
        String result = rbtMap.getValue(keyToGet);

        //then
        assertThat(result).isEqualTo(valueToPut);
    }

    @Test
    public void should_ReturnCorrectValue_WhenThereAreManyKeyss() {
        //given
        RbtMap<Integer, String> rbtMap = new RbtMap<>();
        Integer[] keysToPut = {500, 700, 400, 600};
        String[] valuesToPut = {"should", "return", "correct", "value"};
        Integer keyToGet = 600;
        String expectedValue = "value";

        for (int i = 0; i < keysToPut.length; i++) {
            rbtMap.setValue(keysToPut[i], valuesToPut[i]);
        }

        //when
        String result = rbtMap.getValue(keyToGet);

        //then
        assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    public void should_ReturnCorrectValue_WhenTheValueIsReplaced() {
        //given
        RbtMap<Integer, String> rbtMap = new RbtMap<>();
        Integer[] keysToPut = {500, 700, 400, 600, 700};
        String[] valuesToPut = {"should", "return", "correct", "value", "replaced"};
        Integer keyToGet = 700;
        String expectedValue = "replaced";

        for (int i = 0; i < keysToPut.length; i++) {
            rbtMap.setValue(keysToPut[i], valuesToPut[i]);
        }

        //when
        String result = rbtMap.getValue(keyToGet);

        //then
        assertThat(result).isEqualTo(expectedValue);
    }
}
