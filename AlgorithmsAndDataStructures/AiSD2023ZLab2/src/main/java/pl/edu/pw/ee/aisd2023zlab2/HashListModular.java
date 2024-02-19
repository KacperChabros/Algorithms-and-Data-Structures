package pl.edu.pw.ee.aisd2023zlab2;

public class HashListModular<T extends Comparable<T>>
{
    private static final int DEFAULT_SIZE = 149;
    private final Elem<T> nil = null;

    private Elem<T>[] hashElems;
    private int nElem;
    final int size;

    private class Elem<T extends Comparable<T>>
    {
        private T value;
        private Elem<T> next;
        Elem(T value, Elem<T> next)
        {
            verifyValue(value);
            this.value = value;
            this.next = next;
        }
        private void verifyValue(T value)
        {
            if (value == null)
                throw new IllegalArgumentException("Value cannot be null!");
        }
    }

    public HashListModular(int size)
    {
        verifyInitialSize(size);

        this.size = size;
        hashElems = new Elem[size];
    }

    public HashListModular()
    {
        this(DEFAULT_SIZE);
    }

    private void verifyInitialSize(int size)
    {
        if (size < 1)
            throw new IllegalArgumentException("Hash size cannot be less than \"1\"!");
    }

    private void verifyValue(T value)
    {
        if (value == null)
            throw new IllegalArgumentException("Value of elem in hash table cannot be null!");
    }

    public T get(T value)
    {
        verifyValue(value);

        int hashId = countHashId(value);

        Elem<T> currentElem = hashElems[hashId];

        while (currentElem != nil && currentElem.value.compareTo(value) != 0)
        {
            currentElem = currentElem.next;
        }

        if(currentElem != nil)
        {
            return currentElem.value;
        }
        else
        {
            return null;
        }
    }
    
    public void add(T value)
    {
        verifyValue(value);

        int hashId = countHashId(value);
        Elem<T> currentElem = hashElems[hashId];

        while (currentElem != nil && currentElem.value.compareTo(value) != 0)
        {
            currentElem = currentElem.next;
        }

        if (currentElem != nil)
        {
            currentElem.value = value;
        }
        else
        {
            hashElems[hashId] = new Elem<>(value, hashElems[hashId]);
            nElem++;
        }
    }

    public void delete(T value)
    {
        verifyValue(value);
        
        int hashId = countHashId(value);
        Elem<T> prevElem = nil;
        Elem<T> elem = hashElems[hashId];

        while (elem != nil)
        {
            if (elem.value.compareTo(value) == 0)
            {
                if (prevElem != nil)
                {
                    prevElem.next = elem.next;
                } 
                else
                {
                    hashElems[hashId] = elem.next;
                }
                nElem--;
                break;
            }
            prevElem = elem;
            elem = elem.next;
        }
    }

    private int countHashId(T value)
    {
        int hashCode = value.hashCode();
        return (hashCode & Integer.MAX_VALUE) % size;
    }
    
    public double countLoadFactor() 
    {
        return (double) nElem / size;
    }
}
