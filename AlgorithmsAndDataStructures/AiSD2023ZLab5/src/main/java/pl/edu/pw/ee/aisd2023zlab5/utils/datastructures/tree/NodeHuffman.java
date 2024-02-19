package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree;

public class NodeHuffman implements Comparable<NodeHuffman> {

    private Byte symbol;
    private Integer frequency;
    private NodeHuffman left;
    private NodeHuffman right;

    public NodeHuffman(Byte symbol, Integer frequency, NodeHuffman left, NodeHuffman right) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(NodeHuffman o) {
        if (this.frequency == null) {
            if (o.frequency != null) {
                return 1;
            } else {
                return - 1;
            }
        }
        return this.frequency.compareTo(o.frequency);
    }

    public Byte getSymbol() {
        return symbol;
    }

    public int getFrequency() {
        return frequency;
    }

    public NodeHuffman getLeft() {
        return left;
    }

    public NodeHuffman getRight() {
        return right;
    }

    public void setSymbol(Byte symbol) {
        this.symbol = symbol;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setLeft(NodeHuffman left) {
        this.left = left;
    }

    public void setRight(NodeHuffman right) {
        this.right = right;
    }
}
