package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.utils.GeneralSortTest;

public class QuickSortIterativeMedian3Test extends GeneralSortTest{
    
    public QuickSortIterativeMedian3Test(){
        super(new QuickSortIterativeMedian3());
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIs2ElementsAndSortedAscendingly(){
        //given
        int size = 2;
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
    public void should_CorrectlyAscendingSort_WhenInputIs2ElementsAndSortedDescendingly(){
        //given
        int size = 2;
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
    public void should_CorrectlyAscendingSort_WhenInputIs2ElementsAndRandom(){
        //given
        int size = 2;
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
    public void should_ReturnTheSameArray_WhenInputIs2TheSameElements(){
        //given
        int size = 2;
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
    public void should_CorrectlyAscendingSort_WhenInputIs2NegativeAndPositiveElements(){
        //given
        double[] nums = {1, -5};
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIs3ElementsAndSortedAscendingly(){
        //given
        int size = 3;
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
    public void should_CorrectlyAscendingSort_WhenInputIs3ElementsAndSortedDescendingly(){
        //given
        int size = 3;
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
    public void should_CorrectlyAscendingSort_WhenInputIs3ElementsAndRandom(){
        //given
        int size = 3;
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
    public void should_ReturnTheSameArray_WhenInputIs3TheSameElements(){
        //given
        int size = 3;
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
    public void should_CorrectlyAscendingSort_WhenInputIs3NegativeAndPositiveElements(){
        //given
        double[] nums = {0, 5, -4.5};
        double[] numsCopy = nums.clone();
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
}
