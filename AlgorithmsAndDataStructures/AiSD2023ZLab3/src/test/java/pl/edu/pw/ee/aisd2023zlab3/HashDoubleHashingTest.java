package pl.edu.pw.ee.aisd2023zlab3;

import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getDeletedElem;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getHashElems;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public class HashDoubleHashingTest extends HashGeneralTest{
    
    public HashDoubleHashingTest() {
        super(HashDoubleHashing.class);
    }
    
    
    @Test
    public void should_CorrectlyAddNewElems_WhenThereAreCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Integer[] {null, 65, 555, null, 35, 5, 45, 55, 5555, 15};
        
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
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Integer[] {null, null, null, 5555, null, 65, null, 15, null, 55, null, 5, 35, 45, 55555, 555, null, null, null, null};
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
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {null, 65, 555, null, 35, 5, 45, 55, 5555, tombstone};
        int elemToDelete = 15;
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
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {null, null, null, 5555, null, 65, null, 15, null, 55, null, 5, 35, 45, tombstone, 555, null, null, null, null};
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
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {0, null, null, 5555, null, 18865, null, 15, null, 55, null, 5, 35, 133, tombstone, 555, null, null, null, null};
        
        for(int elem : elementsToPut)
        {
            hashTable.put(elem);
        }
        hashTable.delete(45);
        hashTable.delete(55555);
        hashTable.delete(65);
        //when
        hashTable.put(133);
        hashTable.put(18865);
        hashTable.put(0);
        //then
        int numberOfElems = getNumOfElems(hashTable);
        Comparable[] hashElems = getHashElems(hashTable);
        
        assertThat(numberOfElems).isEqualTo(elementsToPut.length - 3 + 3);
        
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
        Comparable[] expectedTable = new Comparable[]{null, 5, null, null, null, 5, null, null, null, null};
        
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
        Comparable[] expectedTable = new Comparable[]{null, tombstone, null, null, null, tombstone, null, null, null, null};
        
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
