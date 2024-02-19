package pl.edu.pw.ee.aisd2023zlab3;


public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    private final double A;
    private final double B;
    
    public HashQuadraticProbing() {
        super();
        A = 2;
        B = 3;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        if(a < 0 || b <= 0)
            throw new IllegalArgumentException("A can't be less than 0 and B can't be less or equal 0");
        A = a;
        B = b;
    }
    
    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        key = key & Integer.MAX_VALUE;
        
        return (int) (( (key % m ) + A*i + B*i*i ) % m);
    }

}
