package pl.edu.pw.ee.aisd2023zlab3;

import java.util.NoSuchElementException;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

public abstract class HashOpenAddressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private final DeletedElement tombstone = new DeletedElement();
    
    private class DeletedElement implements Comparable<DeletedElement>{    
        public DeletedElement(){
        }
        @Override
        public int compareTo(DeletedElement o) {
            return 0;
        }
    }

    HashOpenAddressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAddressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = createTable(this.size);
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != tombstone) {
            if (i + 1 == size) {
                doubleResize();
                i = -1;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        
        int i = 0;
        int key = elem.hashCode();
        int hashId = hashFunc(key, i);
        
        while( ( hashElems[hashId] != nil ||  tombstone.equals(hashElems[hashId]) ) && !elem.equals(hashElems[hashId])){
            i = (i+1) % size;
            hashId = hashFunc(key, i);
        }
        
        if(elem.equals(hashElems[hashId]))
            return hashElems[hashId];
        else
            throw new NoSuchElementException("There is no such element in the HashTable");
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);
        
        int i = 0;
        int key = elem.hashCode();
        int hashId = hashFunc(key, i);
        
        while( ( hashElems[hashId] != nil ||  tombstone.equals(hashElems[hashId]) ) && !elem.equals(hashElems[hashId]) ){
            i = (i+1) % size;
            hashId = hashFunc(key, i);
        }
        
        if(elem.equals(hashElems[hashId])){
            hashElems[hashId] = (T) tombstone;
            nElems--;
        }else{
            throw new NoSuchElementException("There is no such element to delete in the HashTable");
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private T[] createTable(int size) {
        return (T[]) new Comparable[size];
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;

        T[] oldElems = hashElems;
        hashElems = createTable(size);
        nElems = 0;

        T currentElem;
        for (int i = 0; i < oldElems.length; i++) {
            currentElem = oldElems[i];

            if (currentElem != nil) {
                put(currentElem);
                oldElems[i] = nil;
            }
        }
    }
}
