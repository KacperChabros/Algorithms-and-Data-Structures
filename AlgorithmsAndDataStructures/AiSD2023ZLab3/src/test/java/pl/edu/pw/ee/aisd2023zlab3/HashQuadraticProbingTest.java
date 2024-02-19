package pl.edu.pw.ee.aisd2023zlab3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getDeletedElem;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getHashElems;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getAConstant;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getBConstant;

public class HashQuadraticProbingTest extends HashGeneralTest{
    
    public HashQuadraticProbingTest() {
        super(HashQuadraticProbing.class);
    }
    
    @Test
    public void should_SetCorrectConstants_WhenParameterlessConstructorIsInvoked()
    {
        //given
        double expectedA = 2;
        double expectedB = 3;
        
        
        //when
        HashQuadraticProbing<Integer> hashTable = new HashQuadraticProbing<>();
        
        //then
        double actualA = getAConstant(hashTable);
        double actualB = getBConstant(hashTable);
        
        assertThat(actualA).isEqualTo(expectedA);
        assertThat(actualB).isEqualTo(expectedB);
    }
    
    @Test
    public void should_SetCorrectConstants_WhenConstructorWithParametersIsInvoked()
    {
        //given
        double expectedA = 5;
        double expectedB = 11;
        
        
        //when
        HashQuadraticProbing<Integer> hashTable = new HashQuadraticProbing<>(10, expectedA, expectedB);
        
        //then
        double actualA = getAConstant(hashTable);
        double actualB = getBConstant(hashTable);
        
        assertThat(actualA).isEqualTo(expectedA);
        assertThat(actualB).isEqualTo(expectedB);
    }
    
    @Test
    public void should_ThrowException_WhenAIsLowerThan0() {
        // given
        int initialSize = 10;
        double a = -1;
        double b = 5;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            createEmptyHashTable(initialSize, a, b);
        });

        // then
        String message = "A can't be less than 0 and B can't be less or equal 0";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_ThrowException_WhenBIs0OrLower() {
       // given
        int initialSize = 10;
        double a = 5;
        double b = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            createEmptyHashTable(initialSize, a, b);
        });

        // then
        String message = "A can't be less than 0 and B can't be less or equal 0";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
    
    @Test
    public void should_CorrectlyAddNewElems_WhenThereAreCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35};
        HashTable<Integer> hashTable = createEmptyHashTable(10, 3, 2);
        Comparable[] expectedTable = new Integer[] {55, null, 5555, null, 15, 5, null, 35, null, 555 };
        
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
    public void should_CorrectlyAddNewElems_WhenThereAreCollisionsAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65, 55555};
        HashTable<Integer> hashTable = createEmptyHashTable(10, 3, 2);
        Comparable[] expectedTable = new Integer[] {5555, null, 35, null, null, 5, null, null, null, 15, 45, null, 65, null, 55555, 55, null, null, null, 555};
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
    public void should_CorrectlyDelete_WhenThereAreCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35};
        HashTable<Integer> hashTable = createEmptyHashTable(10, 3, 2);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {55, null, tombstone, null, 15, 5, null, 35, null, 555 };
        int elemToDelete = 5555;
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
    public void should_CorrectlyDelete_WhenThereAreCollisionsAndANeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65, 55555};
        HashTable<Integer> hashTable = createEmptyHashTable(10, 3, 2);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {5555, null, 35, null, null, 5, null, null, null, 15, 45, null, 65, null, tombstone, 55, null, null, null, 555};
        
        int elemToDelete = 55555;
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
    public void should_CorrectlyPutElem_WhenThereAreDeletedItems()
    {
       //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65, 55555};
        HashTable<Integer> hashTable = createEmptyHashTable(10, 3, 2);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {1555, null, 35, null, null, 5, null, null, null, 15, 45, null, tombstone, null, 55555, 12355, null, null, null, 555};
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        hashTable.delete(55);
        hashTable.delete(5555);
        hashTable.delete(65);
        //when
        hashTable.put(12355);
        hashTable.put(1555);
        
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length - 3 + 2);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    @Test
    public void should_CorrectlyPut_Duplicates()
    {
        //given
        int elemToAdd = 5;
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Comparable[]{5, null, null, null, null, 5, null, null, null, null};
        
        //when
        hashTable.put(elemToAdd);
        hashTable.put(elemToAdd);
        //then        
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(2);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
    @Test
    public void should_CorrectlyDelete_WhenThereAreDuplicates()
    {
        //given
        int elemToDelete = 5;
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        hashTable.put(elemToDelete);
        hashTable.put(elemToDelete);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[]{tombstone, null, null, null, null, tombstone, null, null, null, null};
        
        //when
        hashTable.delete(elemToDelete);
        hashTable.delete(elemToDelete);

        //then        
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(0);
        
        assertThat(hashElems)
                .hasSize(expectedTable.length)
                .containsExactly(expectedTable);
    }
    
}
