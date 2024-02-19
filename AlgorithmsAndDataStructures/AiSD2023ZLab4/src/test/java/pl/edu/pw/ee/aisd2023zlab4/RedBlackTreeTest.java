package pl.edu.pw.ee.aisd2023zlab4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static utils.AdvancedGetters.getMethodWithNodeParameter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static utils.AdvancedGetters.getRoot;
import static utils.NodeProviders.createNode;
import static utils.NodeProviders.deepCopyNode;

public class RedBlackTreeTest {

    @Test
    public void should_NotChangeColors_WhenBothChildrenAreBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "changeColorsIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method changeColorsIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        changeColorsIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(parent).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_NotChangeColors_WhenRightChildIsBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "changeColorsIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.RED, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method changeColorsIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        changeColorsIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(parent).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_NotChangeColors_WhenLeftChildIsBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "changeColorsIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method changeColorsIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        changeColorsIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(parent).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }

    @Test
    public void should_ChangeColors_WhenBothChildrenAreRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "changeColorsIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.RED, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedLeftChild = createNode(4, "left", Color.BLACK, null, null);
        Node<Integer, String> expectedRightChild = createNode(7, "right", Color.BLACK, null, null);
        Node<Integer, String> expectedParent = createNode(5, "parent", Color.RED, expectedLeftChild, expectedRightChild);
        
        Method changeColorsIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        changeColorsIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(parent).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_NotRotateLeft_WhenBothChildrenAreBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateLeftIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.BLACK, null, null);
        Node<Integer, String> leftChildOfRight = createNode(6, "leftChildOfRight", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfRight = createNode(8, "rightChilfOfRight", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, leftGrandchild, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, leftChildOfRight, rightChildOfRight);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method rotateLeftIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rotateLeftIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_NotRotateLeft_WhenRightChildIsBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateLeftIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.BLACK, null, null);
        Node<Integer, String> leftChildOfRight = createNode(6, "leftChildOfRight", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfRight = createNode(8, "rightChilfOfRight", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "left", Color.RED, leftGrandchild, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, leftChildOfRight, rightChildOfRight);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method rotateLeftIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rotateLeftIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_RotateLeft_WhenRightChildIsRedAndParentIsBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateLeftIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.BLACK, null, null);
        Node<Integer, String> leftChildOfRight = createNode(6, "leftChildOfRight", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfRight = createNode(8, "rightChilfOfRight", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, leftGrandchild, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, leftChildOfRight, rightChildOfRight);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedDescendantsOfLeftChild = deepCopyNode(leftChild);
        Node<Integer, String> expectedRightChild = deepCopyNode(rightChildOfRight);
        Node<Integer, String> expectedRightChildOfLeftChild = deepCopyNode(leftChildOfRight);
        Node<Integer, String> expectedLeftChild = createNode(5, "parent", Color.RED, expectedDescendantsOfLeftChild, expectedRightChildOfLeftChild);
        Node<Integer, String> expectedParent = createNode(7, "right", Color.BLACK, expectedLeftChild, expectedRightChild);
        
        Method rotateLeftIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rotateLeftIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_RotateLeft_WhenRightChildIsRedAndParentIsRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateLeftIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.RED, null, null);
        Node<Integer, String> leftChildOfRight = createNode(6, "leftChildOfRight", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfRight = createNode(8, "rightChilfOfRight", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, leftGrandchild, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, leftChildOfRight, rightChildOfRight);
        Node<Integer, String> parent = createNode(5, "parent", Color.RED, leftChild, rightChild);
        
        Node<Integer, String> expectedDescendantsOfLeftChild = deepCopyNode(leftChild);
        Node<Integer, String> expectedRightChild = deepCopyNode(rightChildOfRight);
        Node<Integer, String> expectedRightChildOfLeftChild = deepCopyNode(leftChildOfRight);
        Node<Integer, String> expectedLeftChild = createNode(5, "parent", Color.RED, expectedDescendantsOfLeftChild, expectedRightChildOfLeftChild);
        Node<Integer, String> expectedParent = createNode(7, "right", Color.RED, expectedLeftChild, expectedRightChild);
        
        Method rotateLeftIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rotateLeftIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_NotRotateRight_WhenLeftChildIsRedAndLeftGrandchildIsBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateRightIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.BLACK, null, null);
        Node<Integer, String> rightChildOfLeftChild = createNode(6, "rightChildOfLeft", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "leftChild", Color.RED, leftGrandchild, rightChildOfLeftChild);
        Node<Integer, String> rightChild = createNode(8, "rightChild", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(7, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method rightRotateIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rightRotateIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_RotateRight_WhenLeftChildIsRedAndLeftGrandchildIsRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "rotateRightIfNeeded";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(3, "leftGC", Color.RED, null, null);
        Node<Integer, String> rightChildOfLeftChild = createNode(6, "rightChildOfLeft", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(4, "leftChild", Color.RED, leftGrandchild, rightChildOfLeftChild);
        Node<Integer, String> rightChild = createNode(8, "rightChild", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(7, "parent", Color.BLACK, leftChild, rightChild);
        
        Node<Integer, String> expectedRightChildOfRight = deepCopyNode(rightChild);
        Node<Integer, String> expectedLeftChildOfRight = deepCopyNode(rightChildOfLeftChild);
        Node<Integer, String> expectedLeftChild = deepCopyNode(leftGrandchild);
        Node<Integer, String> expectedRightChild = createNode(7, "parent", Color.RED, expectedLeftChildOfRight, expectedRightChildOfRight);
        Node<Integer, String> expectedParent = createNode(4, "leftChild", Color.BLACK, expectedLeftChild, expectedRightChild);
        
        Method rightRotateIfNeededMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) rightRotateIfNeededMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_NotReorganizeTree_WhenAllNodesAreBlack() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "reorganizeTree";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method reorganizeTreeMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) reorganizeTreeMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);     
    }
    
    @Test
    public void should_NotReorganizeTree_WhenOnlyLeftChildIsRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "reorganizeTree";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.RED, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.BLACK, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedPartOfTree = deepCopyNode(parent);
        
        Method reorganizeTreeMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) reorganizeTreeMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedPartOfTree);
    }
    
    @Test
    public void should_OnlyChangeColors_WhenBothChildrenAreRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "reorganizeTree";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.RED, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedParent = deepCopyNode(parent);
        expectedParent.setColor(Color.RED);
        expectedParent.getLeft().setColor(Color.BLACK);
        expectedParent.getRight().setColor(Color.BLACK);
        
        Method reorganizeTreeMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) reorganizeTreeMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_OnlyRotateLeft_WhenRightChildIsRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "reorganizeTree";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftChild = createNode(4, "left", Color.BLACK, null, null);
        Node<Integer, String> rightChild = createNode(7, "right", Color.RED, null, null);
        Node<Integer, String> parent = createNode(5, "parent", Color.BLACK, leftChild, rightChild);

        Node<Integer, String> expectedLeftGrandChild = deepCopyNode(leftChild);
        Node<Integer, String> expectedLeftChild = createNode(5, "parent", Color.RED, expectedLeftGrandChild, null);
        Node<Integer, String> expectedParent = createNode(7, "right", Color.BLACK, expectedLeftChild, null);
        
        Method reorganizeTreeMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) reorganizeTreeMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    
    @Test
    public void should_RotateRightAndSwapColors_WhenLeftChildAndLeftGrandchildAreRed() throws IllegalAccessException, InvocationTargetException
    {
        //given
        String testedMethod = "reorganizeTree";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Node<Integer, String> leftGrandchild = createNode(4, "leftGC", Color.RED, null, null);
        Node<Integer, String> rightChildOfLeftChild = createNode(8, "rightOfLeft", Color.BLACK, null, null);
        Node<Integer, String> leftChild = createNode(7, "left", Color.RED, leftGrandchild, rightChildOfLeftChild);
        Node<Integer, String> parent = createNode(10, "parent", Color.BLACK, leftChild, null);

        
        Node<Integer, String> expectedLeftChildOfRightChild = deepCopyNode(rightChildOfLeftChild);
        Node<Integer, String> expectedLeftChild = createNode(4, "leftGC", Color.BLACK, null, null);
        Node<Integer, String> expectedRightChild = createNode(10, "parent", Color.BLACK, expectedLeftChildOfRightChild, null);
        Node<Integer, String> expectedParent = createNode(7, "left", Color.RED, expectedLeftChild, expectedRightChild);
        
        Method reorganizeTreeMethod = getMethodWithNodeParameter(tree, testedMethod);
        
        //when
        Node<Integer,String> result = (Node<Integer,String>) reorganizeTreeMethod.invoke(tree, parent);
        
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedParent);
    }
    
    @Test
    public void should_ThrowException_WhenPutKeyIsNull()
    {
        //given
        Integer keyToPut = null;
        String valueToPut = "value";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        // when
        Throwable exceptionCaught = catchThrowable(() -> {
               tree.put(keyToPut, valueToPut);
        });

        // then
        String message = "Input params (key, value) cannot be null.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_ThrowException_WhenPutValueIsNull()
    {
        //given
        Integer keyToPut = 5;
        String valueToPut = null;
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        // when
        Throwable exceptionCaught = catchThrowable(() -> {
               tree.put(keyToPut, valueToPut);
        });

        // then
        String message = "Input params (key, value) cannot be null.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    
    @Test
    public void should_CorrectlyPut_WhenThereIsEmptyTree()
    {
        //given
        Integer keyToPut = 10;
        String valueToPut = "root";
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedTree = createNode(keyToPut, valueToPut, Color.BLACK, null, null);
        
        //when
        tree.put(keyToPut, valueToPut);
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_CorrectlyPut_OnTheLeft()
    {
        //given
        Integer[] keysToPut = {10, 5};
        String[] valuesToPut = {"root", "left"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[1], valuesToPut[1], Color.RED, null, null);
        Node<Integer, String> expectedTree = createNode(keysToPut[0], valuesToPut[0], Color.BLACK, expectedLeftChild, null);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_CorrectlyPut_OnTheRight()
    {
        //given
        Integer[] keysToPut = {10, 15};
        String[] valuesToPut = {"root", "right"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[0], valuesToPut[0], Color.RED, null, null);
        Node<Integer, String> expectedTree = createNode(keysToPut[1], valuesToPut[1], Color.BLACK, expectedLeftChild, null);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_CorrectlyPut_OnTheLeftAndRight()
    {
        //given
        Integer[] keysToPut = {10, 5, 15};
        String[] valuesToPut = {"root", "left", "right"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[1], valuesToPut[1], Color.BLACK, null, null);
        Node<Integer, String> expectedRightChild = createNode(keysToPut[2], valuesToPut[2], Color.BLACK, null, null);
        Node<Integer, String> expectedTree = createNode(keysToPut[0], valuesToPut[0], Color.BLACK, expectedLeftChild, expectedRightChild);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_CorrectlyPut_OnTheLeftAndLeft()
    {
        //given
        Integer[] keysToPut = {10, 5, 4};
        String[] valuesToPut = {"root", "left", "leftGrandchild"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedRightChild = createNode(keysToPut[0], valuesToPut[0], Color.BLACK, null, null);
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[2], valuesToPut[2], Color.BLACK, null, null);
        Node<Integer, String> expectedTree = createNode(keysToPut[1], valuesToPut[1], Color.BLACK, expectedLeftChild, expectedRightChild);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
        @Test
    public void should_CorrectlyPut_WhenAllFixupsOccur()
    {
        //given
        Integer[] keysToPut = {10, 6, 4, 3, 5};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "just three", "high five"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedLeftChildOfLeft = createNode(keysToPut[3], valuesToPut[3], Color.BLACK, null, null);
        Node<Integer, String> expectedRightChildOfLeft = createNode(keysToPut[4], valuesToPut[4], Color.BLACK, null, null);
        Node<Integer, String> expectedRightChild = createNode(keysToPut[0], valuesToPut[0], Color.BLACK, null, null);
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[2], valuesToPut[2], Color.RED, expectedLeftChildOfLeft, expectedRightChildOfLeft);
        Node<Integer, String> expectedTree = createNode(keysToPut[1], valuesToPut[1], Color.BLACK, expectedLeftChild, expectedRightChild);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_ReplaceValue_WhenKeyIsTheSame()
    {
        //given
        Integer[] keysToPut = {10, 5, 4, 4};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "I'M REPLACED"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        Node<Integer, String> expectedRightChild = createNode(keysToPut[0], valuesToPut[0], Color.BLACK, null, null);
        Node<Integer, String> expectedLeftChild = createNode(keysToPut[3], valuesToPut[3], Color.BLACK, null, null);
        Node<Integer, String> expectedTree = createNode(keysToPut[1], valuesToPut[1], Color.BLACK, expectedLeftChild, expectedRightChild);
        
        //when
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //then
        Node<Integer, String> actualTree = (Node<Integer, String>) getRoot(tree);
        
        assertThat(actualTree).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
    
    @Test
    public void should_ThrowException_WhenGetKeyIsNull()
    {
        //given
        Integer keyToGet = null;
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        // when
        Throwable exceptionCaught = catchThrowable(() -> {
               tree.get(keyToGet);
        });

        // then
        String message = "Key cannot be null.";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_ReturnNull_WhenTreeIsEmpty()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet = 5;
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isNull();
    }
    
    @Test
    public void should_ReturnNull_WhenThereIsNoSuchElementInTheTree()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet = 5;
        tree.put(15, "who am I?");
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isNull();
    }
    
    @Test
    public void should_ReturnCorrectValue_WhenTheKeyIsTheSmallest()
    {
        //given
        Integer[] keysToPut = {10, 6, 4, 3, 5};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "just three", "high five"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet = 3;
        String expectedValue = "just three";
        
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isEqualTo(expectedValue);
    }
    
    @Test
    public void should_ReturnCorrectValue_WhenTheKeyIsTheLargest()
    {
        //given
        Integer[] keysToPut = {10, 6, 4, 3, 5};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "just three", "high five"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet = 10;
        String expectedValue = "root";
        
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isEqualTo(expectedValue);
    }
    
    @Test
    public void should_ReturnCorrectValue_WhenTheKeyIsSomewhereInTheMiddle()
    {
        //given
        Integer[] keysToPut = {10, 6, 4, 3, 5};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "just three", "high five"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet1 = 4;
        String expectedValue1 = "leftGrandchild";
        Integer keyToGet2 = 5;
        String expectedValue2 = "high five";
        
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //when
        String result1 = tree.get(keyToGet1);
        String result2 = tree.get(keyToGet2);
        
        //then
        assertThat(result1).isEqualTo(expectedValue1);
        assertThat(result2).isEqualTo(expectedValue2);
    }
    
    @Test
    public void should_ReturnCorrectValue_WhenTheKeyIsRoot()
    {
        //given
        Integer[] keysToPut = {10, 6, 4, 3, 5};
        String[] valuesToPut = {"root", "left", "leftGrandchild", "just three", "high five"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet =6;
        String expectedValue = "left";
        
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isEqualTo(expectedValue);
    }
   
    @Test
    public void should_CorrectlyGetValue_WhenTheValueWasReplaced()
    {
        //given
        Integer[] keysToPut = {10, 10};
        String[] valuesToPut = {"root", "left"};
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Integer keyToGet =10;
        String expectedValue = "left";
        
        for(int i=0; i<keysToPut.length; i++)
        {
            tree.put(keysToPut[i], valuesToPut[i]);
        }
        
        //when
        String result = tree.get(keyToGet);
        
        //then
        assertThat(result).isEqualTo(expectedValue);
    }
    
    @Test
    public void should_ReturnNull_WhenThereIsNothingToDelete()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        
        //when
        tree.deleteMin();
        
        //then
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        
        assertThat(result).isNull();
    }
    
    @Test
    public void should_ReturnNull_WhenThereIsOnlyRoot()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.put(256, "value");
        
        //when
        tree.deleteMin();
        
        //then
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        
        assertThat(result).isNull();
    }
    
    @Test
    public void should_CorrectlyDelete_WhenThereAreManyElems()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.put(100, "value");
        tree.put(80, "value1");
        tree.put(110, "value2");
        tree.put(105, "value5");
        
        Node<Integer, String> expectedLeft = createNode(100, "value", Color.BLACK, null, null);
        Node<Integer, String> expectedRight = createNode(110, "value2", Color.BLACK, null, null);
        Node<Integer, String> expectedRoot = createNode(105, "value5", Color.BLACK, expectedLeft, expectedRight);
        
        //when
        tree.deleteMin();

        //then
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedRoot);
        
    }
    
    @Test
    public void should_CorrectlyDelete_WhenThereAre3Elems()
    {
        //given
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.put(127, "value");
        tree.put(5, "value1");
        tree.put(150, "value5");
        
        Node<Integer, String> expectedLeft = createNode(127, "value", Color.RED, null, null);
        Node<Integer, String> expectedRoot = createNode(150, "value5", Color.BLACK, expectedLeft, null);
        
        //when
        tree.deleteMin();

        //then
        Node<Integer, String> result = (Node<Integer, String>) getRoot(tree);
        
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedRoot);
        
    }
}
