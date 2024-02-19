package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.utils.GeneralSortTest;

public class QuickSortIterativeWithInSortTest extends GeneralSortTest{
    
    public QuickSortIterativeWithInSortTest(){
        super(new QuickSortIterativeWithInSort());
    }   
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIs25ElementsAndSortedAscendingly(){
        //given
        int size = 25;
        double[] nums = createAscendinglySortedData(size);
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIs25ElementsAndSortedDescendingly(){
        //given
        int size = 25;
        double[] nums = createDescendinglySortedData(size);
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIs25ElementsAndRandom(){
        //given
        int size = 25;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_ReturnTheSameArray_WhenInputIs25TheSameElements(){
        //given
        int size = 25;
        double value = 5.5;
        double[] nums = createSameElementsData(size, value);
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .hasSize(size)
                .containsOnly(value);
    }
    
    @Test
    public void should_ReturnTheSameArray_WhenInputIs25NegativeAndPositive(){
        //given
        int size = 25;
        double[] nums = createNegativeAndPositiveNumbersData(size);
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
}
