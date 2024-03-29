package pl.edu.pw.ee.aisd2023zlab1;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        int n = nums.length;
        int minValId;
        
        for(int i=0; i<n-1; i++)
        {
            minValId = i;
            for(int j=i+1; j<n; j++)
            {
                if(nums[j] < nums[minValId])
                {
                    minValId = j;
                }
            }
            
            swap(nums, i, minValId);
        }
    }
    
    private void validateParams(double[] nums){
        if(isNull(nums))
            throw new RuntimeException("Input args (nums) cannot be null!");
    }
    
    private void swap(double[] nums, int firstId, int secondId){
        if(firstId != secondId)
        {
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }
}
