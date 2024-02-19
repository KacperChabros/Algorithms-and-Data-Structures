package org.example;

import org.junit.Assert;
import org.junit.Test;

class BSearchTest {

    private BSearch binarySearcher = new BSearch();
    private int numberArray[] = {1, 4, 7, 14, 17, 49, 61, 76, 100, 120};

    @Test
    public void NonExistentShouldBeMinus1(){
        int result = binarySearcher.search(numberArray, 150);
        Assert.assertEquals(-1, result);
    }


}