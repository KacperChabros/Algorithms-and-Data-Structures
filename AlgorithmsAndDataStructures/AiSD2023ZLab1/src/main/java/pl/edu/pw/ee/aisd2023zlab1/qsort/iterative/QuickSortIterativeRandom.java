package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.Random;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class QuickSortIterativeRandom implements Sorting{

    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        quicksort(nums);
    }
    
    private void quicksort(double[] nums){
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        
        Integer leftId = 0;
        Integer rightId = nums.length - 1;

        starts.add(leftId);
        ends.add(rightId);
        
        int n = 1;
        
        if( leftId < rightId ){
            while( n > 0 ){
                n--;
                leftId = starts.remove(n);
                rightId = ends.remove(n);

                int pivotId = partition(nums, leftId, rightId);

               if( pivotId > leftId){
                   starts.add(leftId);
                   ends.add( pivotId );
                   n++;
               }

               if(pivotId + 1 < rightId){
                   starts.add( pivotId + 1 );
                   ends.add(rightId);
                   n++;
               }
            }
        }     
    }
    
    private int partition(double [] nums, int startId, int endId){
        Random rand = new Random();
        int randomPivotId = rand.nextInt(startId, endId + 1 );
        swap(nums, startId, randomPivotId);
        double pivot = nums[startId];
        int leftId = startId - 1;
        int rightId = endId + 1;
        
        while(true){
            while( nums[++leftId] < pivot){
            }
            
            while( nums[--rightId] > pivot){
            }
            
            if(leftId < rightId ){
                swap(nums, leftId, rightId);
            }else{
                break;
            }
        }
        
        return rightId;
    }
    
    private void validateParams(double[] nums){
        if(isNull(nums))
            throw new RuntimeException("Input args (nums) cannot be null!");
    }
     
    private void swap(double[] nums, int firstId, int secondId){
        if( firstId != secondId ){
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }
}
