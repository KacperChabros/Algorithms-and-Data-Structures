package pl.edu.pw.ee.aisd2023zlab3;

import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getDeletedElem;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getHashElems;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public abstract class HashGeneralTest {
    
    private final Class<? extends HashOpenAddressing> hashClass;

    public HashGeneralTest(Class<? extends HashOpenAddressing> hashClass) {
        this.hashClass = hashClass;
    }
    
    @Test
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            createEmptyHashTable(initialSize);
        });

        // then
        String message = "Initial size of hash table cannot be lower than 1!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        
        HashTable<String> unusedNames = null;
        if(hashClass.equals(HashLinearProbing.class))
            unusedNames = new HashLinearProbing<>();
        else if(hashClass.equals(HashQuadraticProbing.class))
            unusedNames = new HashQuadraticProbing<>();
        else if(hashClass.equals(HashDoubleHashing.class))
            unusedNames = new HashDoubleHashing<>();
            
        String newElem = "P. Czarnek";

        // when
        int nOfElemsBeforePut = getNumOfElems(unusedNames);
        unusedNames.put(newElem);
        int nOfElemsAfterPut = getNumOfElems(unusedNames);

        // then
        assertThat(nOfElemsBeforePut).isEqualTo(0);
        assertThat(nOfElemsAfterPut).isEqualTo(1);
    }
    
    
    
    @Test
    public void should_ThrowException_WhenElementToPutIsNull()
    {
        //given
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        String message = "Input elem cannot be null!";
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.put(null);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_ThrowException_WhenElementToGetIsNull()
    {
        //given
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        String message = "Input elem cannot be null!";
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.get(null);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
        @Test
    public void should_ThrowException_WhenElementToDeletetIsNull()
    {
        //given
        HashTable<Integer> hashTable = createEmptyHashTable(2039);
        String message = "Input elem cannot be null!";
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.delete(null);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    
    @Test
    public void should_ThrowException_WhenElementToGetDoesntExist()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        String message = "There is no such element in the HashTable";
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.get(128);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(message);
    }
    
    
       @Test
    public void should_ThrowException_WhenElementToDeleteDoesntExist()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable = createEmptyHashTable(2039);
        String message = "There is no such element to delete in the HashTable";
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.delete(128);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_CorrectlyGetElem_WhenThereAreNoCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable =createEmptyHashTable(10);
        int elemToGet = 68;
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        int gotElem = hashTable.get(elemToGet);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(gotElem).isEqualTo(elemToGet);
    }
    
    
    @Test
    public void should_CorrectlyGetElem_WhenThereAreNoCollisionsAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54, 66};
        HashTable<Integer> hashTable =createEmptyHashTable(10);
        int elemToGet = 54;
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        int gotElem = hashTable.get(elemToGet);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(gotElem).isEqualTo(elemToGet);
    }
    
    @Test
    public void should_CorrectlyGetNewElem_WhenThereAreCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65};
        HashTable<Integer> hashTable =createEmptyHashTable(10);
        int elemToGet = 45;
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        int gotElem = hashTable.get(elemToGet);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(gotElem).isEqualTo(elemToGet);
    }
    
    @Test
    public void should_CorrectlyGetElem_WhenThereAreCollisionsAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65, 55555};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        int elemToGet = 55555;
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        int gotElem = hashTable.get(elemToGet);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(gotElem).isEqualTo(elemToGet);
    }
    

    
    @Test
    public void should_CorrectlyGetElem_WhenThereAreDeletedItems()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65, 55555};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        int elemToGet = 5;
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        hashTable.delete(55);
        hashTable.delete(5555);
        //when
        int gotElem = hashTable.get(elemToGet);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length - 2);
        
        assertThat(gotElem).isEqualTo(gotElem);
    }
    
    
    @Test
    public void should_CorrectlyAddNewElems_WhenThereIsNoCollisionAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Integer[] {20, null, 52, 53, 54, 5, null, 67, 68, 19};
        
        //when
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    @Test
    public void should_CorrectlyAddNewElems_WhenThereIsNoCollisionAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54, 66};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Integer[] {20, null, null, null, null, 5, 66, 67, 68, null, null, null, 52, 53, 54, null, null, null, null, 19};
        
        //when
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    
    @Test
    public void should_CorrectlyDeleteElem_WhenThereAreNoCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {20, null, 52, 53, 54, 5, null, 67, tombstone, 19};
        int elemToDelete = 68;
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        hashTable.delete(elemToDelete);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length - 1);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    @Test
    public void should_ThrowException_WhenThereGettingDeletedItem()
    {
        //given
        String message = "There is no such element in the HashTable";
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        int elemToDelete = 68;
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        hashTable.delete(elemToDelete);
        
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.get(elemToDelete);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_CorrectlyDelete_WhenThereAreNoCollisionsAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,20,52,53,68,19,67,54, 66};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {20, null, null, null, null, 5, 66, 67, 68, null, null, null, 52, 53, tombstone, null, null, null, null, 19};
        int elemToDelete = 54;
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        
        //when
        hashTable.delete(elemToDelete);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length - 1);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    @Test
    public void should_CorrectlyGet_WhenThereAreDuplicates()
    {
        //given
        int elemToGet = 5;
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        hashTable.put(elemToGet);
        hashTable.put(elemToGet);
        
        //when
        int gotItem = hashTable.get(elemToGet);
        

        //then        
         int numberOfElems = getNumOfElems(hashTable);


        assertThat(numberOfElems).isEqualTo(2);
        
        assertThat(gotItem).isEqualTo(elemToGet);

    }
 
        @Test
    public void should_ThrowException_WhenTryingToDeleteDeletedDuplicates()
    {
        //given
        String message = "There is no such element to delete in the HashTable";
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        int elemToDelete = 68;
        
        hashTable.put(elemToDelete);
        hashTable.put(elemToDelete);
        hashTable.delete(elemToDelete);
        hashTable.delete(elemToDelete);
        
        //when
        Throwable exceptionCaught = catchThrowable( () ->{
            hashTable.delete(elemToDelete);
        });
        
        //then
        
        assertThat(exceptionCaught)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(message);
    }
    
    protected HashTable<Integer> createEmptyHashTable(int size){
        return createEmptyHashTable(size, 2, 3);
    }
    
    protected HashTable<Integer> createEmptyHashTable(int size, double a, double b){
          if( hashClass.equals(HashLinearProbing.class)){
              return new HashLinearProbing<>(size);
          }
          
          if( hashClass.equals(HashDoubleHashing.class)){
              return new HashDoubleHashing<>(size);
          }
          
          if( hashClass.equals(HashQuadraticProbing.class) ){
              return new HashQuadraticProbing<>(size, a , b);
          }
          
          return null;
    }
}
