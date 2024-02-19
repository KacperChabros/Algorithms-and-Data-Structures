package pl.edu.pw.ee.aisd2023zlab1;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class BubbleSort implements Sorting {
    
    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j + 1] < nums[j]) {
                    double tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }
    
    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }
}
