package pl.edu.pw.ee.aisd2023zlab1;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        
        for(int i=1; i<nums.length; i++){
            double key = nums[i];
            int j = i-1;
            
            while( j>=0 && nums[j] > key){
                nums[j+1] = nums[j];
                j--;
            }
            nums[++j] = key;
        }
    }
    
     private void validateParams(double[] nums){
        if(isNull(nums))
            throw new RuntimeException("Input args (nums) cannot be null!");
    }
    
}
