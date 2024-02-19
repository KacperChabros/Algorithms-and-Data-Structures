package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int numberArray[] = {1, 4, 7, 14, 17, 49, 61, 76, 100, 120};
        int desiredNumber = 100;

        BSearch binarySearcher = new BSearch();


        System.out.println(binarySearcher.search(numberArray, desiredNumber));
    }
}