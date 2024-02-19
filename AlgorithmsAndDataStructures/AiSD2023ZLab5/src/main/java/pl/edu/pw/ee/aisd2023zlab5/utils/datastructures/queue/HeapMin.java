package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.queue;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import java.util.ArrayList;
import java.util.List;

public class HeapMin<T extends Comparable<T>> implements PriorityQueue<T> {

    private final List<T> elements;

    public HeapMin() {
        elements = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public void put(T element) {
        verifyElement(element);
        elements.add(element);
        heapifyBottomUp();
    }

    @Override
    public T pop() {
        if (elements.isEmpty()) {
            throw new HuffmanException("The heap is empty!", HeapMin.class.getName(),
                    "pop()", IndexOutOfBoundsException.class.getName());
        }
        T rootElem = elements.get(0);
        T lastElement = elements.remove(elements.size() - 1);

        if (!elements.isEmpty()) {
            elements.set(0, lastElement);
        }

        heapify();
        return rootElem;
    }

    private void verifyElement(T element) {
        if (element == null) {
            throw new HuffmanException("The element cannot be null!", HeapMin.class.getName(),
                    "verifyElement(T element)", IllegalArgumentException.class.getName());
        }
    }

    private void heapify() {
        int currentId = 0;
        int endId = elements.size();

        while (currentId < endId) {
            int leftId = 2 * currentId + 1;
            int rightId = leftId + 1;
            int smallestId = currentId;

            if (leftId < endId && elements.get(smallestId).compareTo(elements.get(leftId)) > 0) {
                smallestId = leftId;
            }

            if (rightId < endId && elements.get(smallestId).compareTo(elements.get(rightId)) > 0) {
                smallestId = rightId;
            }

            if (smallestId == currentId) {
                break;
            }

            T startElement = elements.get(currentId);
            elements.set(currentId, elements.get(smallestId));
            elements.set(smallestId, startElement);
            currentId = smallestId;
        }
    }

    private void heapifyBottomUp() {
        int currentId = elements.size() - 1;
        int parentId = (currentId - 1) / 2;

        while (currentId >= 0 && parentId >= 0 && elements.get(parentId).compareTo(elements.get(currentId)) > 0) {
            T parent = elements.get(parentId);
            elements.set(parentId, elements.get(currentId));
            elements.set(currentId, parent);
            currentId = parentId;
            parentId = (currentId - 1) / 2;
        }
    }

}
