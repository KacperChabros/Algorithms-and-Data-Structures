package org.example;

public class BSearch {
    int search(int[] nums, int toFind)
    {
        int begin = 0;
        int end = nums.length - 1;

        while( begin <= end )
        {
            int mid = begin + (end - begin) / 2;

            if( toFind > nums[mid] )
                begin = mid + 1;
            else if( toFind < nums[mid] )
                end = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
