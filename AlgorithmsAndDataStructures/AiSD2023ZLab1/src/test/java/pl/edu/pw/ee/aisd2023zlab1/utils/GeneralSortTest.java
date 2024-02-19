package pl.edu.pw.ee.aisd2023zlab1.utils;

import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public abstract class GeneralSortTest {

    protected Sorting sorter;

    public GeneralSortTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_WhenInputIsNull() {
        // given
        double[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (nums) cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnEmptyArray_WhenInputIsEmpty() {
        // given
        double[] nums = {};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).isEmpty();
    }
    
    @Test
    public void should_ReturnTheSameArray_WhenInputHasOneElement(){
        // given
        double[] nums = {5.5};
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums).
                hasSize(1)
                .contains(5.5);
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputLengthIsOdd(){
        //given
        int size = 23;
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
    public void should_CorrectlyAscendingSort_WhenInputIsSortedAscendinglyAndSmall(){
        //given
        int size = 20;
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
    public void should_CorrectlyAscendingSort_WhenInputIsSortedDescendinglyAndSmall(){
        //given
        int size = 20;
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
    public void should_CorrectlyAscendingSort_WhenInputIsRandomAndSmall(){
        //given
        int size = 20;
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
    public void should_ReturnTheSameArray_WhenInputIsSmallAndElementsAreTheSame(){
        //given
        int size = 20;
        double value = 4.5;
        double[] nums = createSameElementsData(size, value);
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .hasSize(size)
                .containsOnly(value);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsNegativeAndPositiveElementsAndSmall() {
        // given
        int size = 20;
        double[] nums = createNegativeAndPositiveNumbersData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsSortedAscendinglyAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createAscendinglySortedData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
     @Test
    public void should_CorrectlyAscendingSort_WhenInputIsSortedDescendinglyAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createDescendinglySortedData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsRandomAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    @Test
    public void should_ReturnTheSameArray_WhenInputIsHugeAndElementsAreTheSame(){
        //given
        int size = 10_000;
        double value = 17.2;
        double[] nums = createSameElementsData(size, value);
        
        //when
        sorter.sort(nums);
        
        //then
        assertThat(nums)
                .hasSize(size)
                .containsOnly(value);
    }
    
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsNegativeAndPositiveElementsAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createNegativeAndPositiveNumbersData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    
    protected double[] createRandomData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }
    
    protected double[] createAscendinglySortedData(int size){
        assert size >= 0;
        
        double[] nums = new double[size];
        
        for(int i = 0; i < size; i++){
            nums[i] = i;
        }
        
        return nums;
    }
    
    protected double[] createDescendinglySortedData(int size){
        assert size >= 0;
        
        double[] nums = new double[size];
        
        for(int i = size - 1; i >= 0; i--){
            nums[i] = i;
        }
        
        return nums;
    }
    
    protected double[] createSameElementsData(int size, double value){
        assert size >=0;
        
        double[] nums = new double[size];
        
        for(int i=0; i<size; i++){
            nums[i] = value;
        }
        
        return nums;
    }
    
    protected double[] createNegativeAndPositiveNumbersData(int size)
    {
        assert size >= 0;
        
        double[] nums = new double[size];
        double min = -5.0;
        double max = 5.0;
        
        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = min + (max - min) * rand.nextDouble();
        }

        return nums;
    }
}
