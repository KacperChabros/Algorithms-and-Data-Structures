package pl.edu.pw.ee.aisd2023zlab3;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getDeletedElem;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getHashElems;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public class HashLinearProbingTest extends HashGeneralTest{

    public HashLinearProbingTest() {
        super(HashLinearProbing.class);
    }

@Test
    public void should_CorrectlyAddNewElems_WhenThereAreCollisionsAndNoNeedToResize()
    {
        //given
        int[] elementsToPut = new int[]{5,55,555,5555,15,35,45,65};
        HashTable<Integer> hashTable = createEmptyHashTable(10);
        Comparable[] expectedTable = new Integer[] {35, 45, 65, null, null, 5, 55, 555, 5555, 15};
        
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
        Comparable[] expectedTable = new Integer[] {55555, null, null, null, null, 45, 65, 5, null, null, null, null, null, null, null, 35, 55, 555, 5555, 15};
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
        Comparable[] expectedTable = new Comparable[] {35, tombstone, 65, null, null, 5, 55, 555, 5555, 15};
        int elemToDelete = 45;
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
        Comparable[] expectedTable = new Comparable[] {tombstone, null, null, null, null, 45, 65, 5, null, null, null, null, null, null, null, 35, 55, 555, 5555, 15};
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
        HashTable<Integer> hashTable =  createEmptyHashTable(10);
        Comparable tombstone = getDeletedElem(hashTable);
        Comparable[] expectedTable = new Comparable[] {55555, null, null, null, null, 45, tombstone, 5, null, null, null, null, null, null, null, 35, 12355, 555, 1555, 15};
        
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
        Comparable[] expectedTable = new Comparable[]{null, null, null, null, null, 5, 5, null, null, null};
        
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
        Comparable[] expectedTable = new Comparable[]{null, null, null, null, null, tombstone, tombstone, null, null, null};
        
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
