package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class TreePreorderResult {

    private int importantBitsInTheLastByte;
    private final List<Byte> treePreorder;

    public TreePreorderResult() {
        treePreorder = new ArrayList<>();
    }

    public int getImportantBitsInTheLastByte() {
        return importantBitsInTheLastByte;
    }

    public List<Byte> getTreePreorder() {
        return treePreorder;
    }

    public void add(Byte value) {
        treePreorder.add(value);
    }

    public void setImportantBitsInTheLastByte(int value) {
        importantBitsInTheLastByte = value;
    }

}
