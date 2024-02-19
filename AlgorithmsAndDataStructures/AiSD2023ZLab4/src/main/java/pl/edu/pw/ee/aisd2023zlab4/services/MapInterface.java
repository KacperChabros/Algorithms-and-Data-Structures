package pl.edu.pw.ee.aisd2023zlab4.services;

public interface MapInterface<K extends Comparable<K>, V> {

    public void setValue(K key, V value);

    public V getValue(K key);
}
