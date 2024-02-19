package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class MergeSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input args (nums) cannot be null!");
        }

        mergeSort(nums);
    }

    public void mergeSort(double[] nums) {
        double[] tmp = new double[nums.length];

        double[] source = nums;
        double[] destination = tmp;

        int currentLength = 2;
        int n = nums.length;

        while (currentLength / 2 < n) {
            for (int beginSourceId = 0; beginSourceId < n; beginSourceId += currentLength) {
                int midSourceId = beginSourceId + currentLength / 2 < n ? beginSourceId + currentLength / 2 : n;
                int endSourceId = beginSourceId + currentLength < n ? beginSourceId + currentLength : n;

                mergeSort(source, destination, beginSourceId, midSourceId, endSourceId);
            }

            tmp = source;
            source = destination;
            destination = tmp;
            currentLength *= 2;
        }

        if (source != nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = source[i];
            }
        }
    }

    private void mergeSort(double[] source, double[] destination, int beginId, int midId, int endId) {
        int originalIter = beginId;
        int leftIter = beginId;
        int rightIter = midId;

        while (leftIter < midId && rightIter < endId) {
            if (source[leftIter] <= source[rightIter]) {
                destination[originalIter++] = source[leftIter++];
            } else {
                destination[originalIter++] = source[rightIter++];
            }
        }

        while (leftIter < midId) {
            destination[originalIter++] = source[leftIter++];
        }

        while (rightIter < endId) {
            destination[originalIter++] = source[rightIter++];
        }
    }

}
