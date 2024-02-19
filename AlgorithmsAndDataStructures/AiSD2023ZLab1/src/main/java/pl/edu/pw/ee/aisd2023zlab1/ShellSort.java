package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class ShellSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input args (nums) cannot be null!");
        }
        int n = nums.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int j = i;
                while (j - gap >= 0 && nums[j - gap] > nums[j]) {
                    swap(nums, j, j - gap);

                    j -= gap;
                }
            }
        }
    }

    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            double tmp = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = tmp;
        }
    }

}
