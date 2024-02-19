package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.pw.ee.aisd2023zlab4.Node;
import pl.edu.pw.ee.aisd2023zlab4.RbtMap;
import pl.edu.pw.ee.aisd2023zlab4.RedBlackTree;

public class AdvancedGetters {
    
    public static RedBlackTree<?,?> getRedBlackTree(RbtMap<?,?> map)
    {
        String fieldName = "tree";
        RedBlackTree<?,?> tree = null;
        try {
            Field field = map.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            tree = (RedBlackTree<?, ?>) field.get(map);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(AdvancedGetters.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tree;
    }
    
    public static Node<?,?> getRoot(RedBlackTree<?,?> tree)
    {
        String fieldName = "root";
        
        Node<?,?> root = null;
        try {
            Field field = tree.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            root =  (Node<?, ?>) field.get(tree);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(AdvancedGetters.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return root;
    }
    
    public static Method getMethodWithNodeParameter(RedBlackTree<?,?> tree, String methodName)
    {
        Class<?>[] typesOfArgs = {Node.class};
        Method method = null;
        try {
            
            method = tree.getClass().getDeclaredMethod(methodName, typesOfArgs);
            method.setAccessible(true);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(AdvancedGetters.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return method;
    }
}
