package pl.edu.pw.ee.aisd2023zlab2;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;

public class HashListMultiplicative<T extends Comparable<T>> implements HashTable<T> {

    private static final int DEFAULT_SIZE = 256;
    final int size;
    private Element<T>[] hashElements;
    private int numberOfElements;
    private final Element<T> nil = null;

    private class Element<T extends Comparable<T>> {

        private T value;
        private Element<T> next;

        Element(T value, Element<T> next) {
            validateParams(value);
            this.value = value;
            this.next = next;
        }

        private void validateParams(T value) {
            if (isNull(value)) {
                throw new IllegalArgumentException("Null value is not valid for Element");
            }
        }
    }

    public HashListMultiplicative(int size) {
        validateInputHashSize(size);

        this.size = size;
        hashElements = new Element[size];
    }

    public HashListMultiplicative() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        validateInputValue(value);

        int hashId = calculateHashId(value);

        Element<T> foundElement = findElement(value, hashId);

        if (foundElement != nil) {
            foundElement.value = value;
        } else {
            hashElements[hashId] = new Element(value, hashElements[hashId]);
            numberOfElements++;
        }
    }

    @Override
    public T get(T value) {
        validateInputValue(value);

        int hashId = calculateHashId(value);

        Element<T> foundElement = findElement(value, hashId);

        return foundElement != nil ? foundElement.value : null;
    }

    @Override
    public void delete(T value) {
        validateInputValue(value);
        int hashId = calculateHashId(value);
        Element<T> previousElement = nil;
        Element<T> currentElement = hashElements[hashId];

        while (currentElement != nil) {
            if (currentElement.value.compareTo(value) == 0) {

                if (previousElement != nil) {
                    previousElement.next = currentElement.next;
                } else {
                    hashElements[hashId] = currentElement.next;
                }

                currentElement.next = nil;
                numberOfElements--;
                break;
            }
            previousElement = currentElement;
            currentElement = currentElement.next;
        }
    }

    public double calculateLoadFactor() {
        return (double) numberOfElements / size;
    }

    private int calculateHashId(T value) {
        int hashCode = value.hashCode();

        double A = (Math.sqrt(5) - 1) / 2;

        return (int) (size * (((hashCode & Integer.MAX_VALUE) * A) % 1));
    }

    private Element<T> findElement(T value, int hashId) {
        Element<T> currentElement = hashElements[hashId];

        while (currentElement != nil && currentElement.value.compareTo(value) != 0) {
            currentElement = currentElement.next;
        }

        return currentElement;
    }

    private void validateInputHashSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Given size must be greater or equal 1!");
        }
    }

    private void validateInputValue(T value) {
        if (isNull(value)) {
            throw new IllegalArgumentException("Parameter \"value\" can't be null");
        }
    }
}
