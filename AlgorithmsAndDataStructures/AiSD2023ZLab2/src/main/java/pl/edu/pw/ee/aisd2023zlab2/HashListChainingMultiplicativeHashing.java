package pl.edu.pw.ee.aisd2023zlab2;

public class HashListChainingMultiplicativeHashing<T extends Comparable<T>> extends HashListChaining<T>{

    
    public HashListChainingMultiplicativeHashing(){
        super();
    }
    
    public HashListChainingMultiplicativeHashing(int size){
        super(size);
    }
    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode();
        
        double A = (Math.sqrt(5) - 1) / 2;
        
        return (int) (size *( ((hashCode & Integer.MAX_VALUE)*A) % 1));
    }
}
