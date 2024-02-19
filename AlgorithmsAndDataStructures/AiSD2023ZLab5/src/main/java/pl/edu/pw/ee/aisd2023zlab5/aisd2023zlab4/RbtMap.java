package pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.services.MapInterface;

public class RbtMap<K extends Comparable<K>, V> implements MapInterface<K, V> {

    private final RedBlackTree<K, V> tree;

    public <K, V> RbtMap() {
        tree = new RedBlackTree<>();
    }

    @Override
    public void setValue(K key, V value) {
        if (key == null || value == null) {
            throw new HuffmanException("Params (key, value) cannot be null.", RbtMap.class.getName(),
                    "setValue(K key, V value)", IllegalArgumentException.class.getName());
        }
        tree.put(key, value);
    }

    @Override
    public V getValue(K key) {
        if (key == null) {
            throw new HuffmanException("Cannot get value by null key.", RbtMap.class.getName(),
                    "getValue(K key)", IllegalArgumentException.class.getName());
        }
        return tree.get(key);
    }

}
