package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree;

public class RecreatedTreeResult {
    private final HuffmanTree tree;
    private final int lastByteOfTree;
    private final int bitsLeftInLastByteOfTree;

    public RecreatedTreeResult(HuffmanTree tree, int lastByteOfTree, int bitsLeftInLastByteOfTree) {
        this.tree = tree;
        this.lastByteOfTree = lastByteOfTree;
        this.bitsLeftInLastByteOfTree = bitsLeftInLastByteOfTree;
    }

    public HuffmanTree getTree() {
        return tree;
    }

    public int getLastByteOfTree() {
        return lastByteOfTree;
    }

    public int getBitsLeftInLastByteOfTree() {
        return bitsLeftInLastByteOfTree;
    }
    
}
