package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.queue;

public interface PriorityQueue<T extends Comparable<T>> {

    public void put(T elem);

    public T pop();

    public int getSize();
}
