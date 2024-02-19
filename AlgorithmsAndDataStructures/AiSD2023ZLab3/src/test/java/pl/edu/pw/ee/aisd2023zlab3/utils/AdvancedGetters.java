package pl.edu.pw.ee.aisd2023zlab3.utils;

import java.lang.reflect.Field;
import pl.edu.pw.ee.aisd2023zlab3.HashQuadraticProbing;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

public class AdvancedGetters {

    public static int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T extends Comparable<T>> T[] getHashElems(HashTable<?> hash)
    {
        String fieldName = "hashElems";
        T[] hashElems = null;
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            
            hashElems = (T[]) field.get(hash);
            
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        return hashElems;
    }
    
    public static <T extends Comparable<T>> T getDeletedElem(HashTable<?> hash)
    {
        String fieldName = "tombstone";
        T deletedItem = null;
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            
            deletedItem = (T) field.get(hash);
            
        }   catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return deletedItem;
    }
    
    public static double getAConstant(HashQuadraticProbing<?> hash)
    {
        String fieldName = "A";
        double a = 0;
        
        try {
            Field field = hash.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            
            a = field.getDouble(hash);
            
        }   catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        return a;
    }
    
     public static double getBConstant(HashQuadraticProbing<?> hash)
    {
        String fieldName = "B";
        double a = 0;
        
        try {
            Field field = hash.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            
            a = field.getDouble(hash);
            
        }   catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        return a;
    }
}
