package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.queue;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.AdvancedGetters.getHeapElements;
import static pl.edu.pw.ee.aisd2023zlab5.testutils.ErrorMessageProvider.createMessage;

public class HeapMinTest {

    @Test
    public void should_ThrowException_WhenPoppingFromEmptyHeap() {
        //given
        PriorityQueue<Integer> queue = new HeapMin<>();

        String message = "The heap is empty!";
        String expectedMessage = createMessage(message, HeapMin.class.getName(),
                "pop()", IndexOutOfBoundsException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            Integer result = queue.pop();
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_ThrowException_WhenAddingNullElement() {
        //given
        PriorityQueue<Integer> queue = new HeapMin<>();

        String message = "The element cannot be null!";
        String expectedMessage = createMessage(message, HeapMin.class.getName(),
                "verifyElement(T element)", IllegalArgumentException.class.getName());
        //when
        Throwable exceptionCaught = catchThrowable(() -> {
            queue.put(null);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(HuffmanException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void should_CorrectlyAdd_WhenOneElementIsAdded() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer element = 5;
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(5));

        //when
        queue.put(element);

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(numberOfElements).isEqualTo(expectedList.size());
        assertThat(result).containsExactlyElementsOf(expectedList);
    }

    @Test
    public void should_CorrectlyAdd_WhenManyElementsAreAdded() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer[] elmentsToPut = new Integer[]{7, 4, 8, 1, 6, 5};
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(1, 4, 5, 7, 6, 8));

        //when
        for (Integer elment : elmentsToPut) {
            queue.put(elment);
        }

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(numberOfElements).isEqualTo(elmentsToPut.length);
        assertThat(result).containsExactlyElementsOf(expectedList);
    }

    @Test
    public void should_CorrectlyReturnElement_WhenThereIsOneInTheHeap() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer element = 5;
        queue.put(element);
        //when
        Integer popped = queue.pop();

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(popped).isEqualTo(element);
        assertThat(numberOfElements).isEqualTo(0);
        assertThat(result).isEmpty();
    }

    @Test
    public void should_CorrectlyReturnAndHeapify_WhenThereAreManyElements() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer[] elmentsToPut = new Integer[]{7, 4, 8, 1, 6, 5};
        Integer expectedPopped = 1;
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(4, 6, 5, 7, 8));
        for (Integer elment : elmentsToPut) {
            queue.put(elment);
        }

        //when
        Integer popped = queue.pop();

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(popped).isEqualTo(expectedPopped);
        assertThat(numberOfElements).isEqualTo(elmentsToPut.length - 1);
        assertThat(result).containsExactlyElementsOf(expectedList);
    }

    @Test
    public void should_CorrectlyPop_AllElements() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer[] elmentsToPut = new Integer[]{7, 4, 8, 1, 6, 5};
        List<Integer> expectedPopped = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 7, 8));
        List<Integer> actualPopped = new ArrayList();
        for (Integer elment : elmentsToPut) {
            queue.put(elment);
        }

        //when
        for (int i = 0; i < elmentsToPut.length; i++) {
            actualPopped.add(queue.pop());
        }

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(numberOfElements).isEqualTo(0);
        assertThat(result).isEmpty();
        assertThat(actualPopped).containsExactlyElementsOf(expectedPopped);
    }

    @Test
    public void should_CorrectlyPop_AllElementsWhenDataIsSorted() {
        //given
        HeapMin<Integer> queue = new HeapMin<>();
        Integer[] elmentsToPut = new Integer[]{5, 4, 3, 2, 1};
        List<Integer> expectedPopped = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> actualPopped = new ArrayList();
        for (Integer elment : elmentsToPut) {
            queue.put(elment);
        }

        //when
        for (int i = 0; i < elmentsToPut.length; i++) {
            actualPopped.add(queue.pop());
        }

        //then
        List<Integer> result = (List<Integer>) getHeapElements(queue);
        int numberOfElements = queue.getSize();

        assertThat(numberOfElements).isEqualTo(0);
        assertThat(result).isEmpty();
        assertThat(actualPopped).containsExactlyElementsOf(expectedPopped);
    }
}
