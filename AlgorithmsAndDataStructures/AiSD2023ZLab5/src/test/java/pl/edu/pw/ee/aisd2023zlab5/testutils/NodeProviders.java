package pl.edu.pw.ee.aisd2023zlab5.testutils;

import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.Color;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.Node;

public final class NodeProviders {

    private NodeProviders() {
    }

    public static <K extends Comparable<K>, V> Node<K, V> createNode(K key, V value, Color color, Node<K, V> left, Node<K, V> right) {
        Node<K, V> newNode = new Node<>(key, value);
        newNode.setColor(color);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    public static <K extends Comparable<K>, V> Node<K, V> deepCopyNode(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        Node<K, V> leftNode = null;
        Node<K, V> rightNode = null;

        if (node.getLeft() != null) {
            leftNode = deepCopyNode(node.getLeft());
        }

        if (node.getRight() != null) {
            rightNode = deepCopyNode(node.getRight());
        }

        Node<K, V> newNode = createNode(node.getKey(), node.getValue(), node.getColor(), leftNode, rightNode);

        return newNode;
    }
}
