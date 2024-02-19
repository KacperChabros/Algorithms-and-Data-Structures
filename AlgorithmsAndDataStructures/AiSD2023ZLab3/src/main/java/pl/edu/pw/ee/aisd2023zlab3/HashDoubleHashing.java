package pl.edu.pw.ee.aisd2023zlab3;


public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }
    
    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        
        key = key & Integer.MAX_VALUE;
        
        return ((h1(key) + i*h2(key)) & Integer.MAX_VALUE) % m;
    }
    
    private int h1(int key)
    {
        int m = getSize();
        return key % m;
    }
    
    private int h2(int key)
    {
        int m = getSize();
        return 1 + key % (m-1);
    }

}
