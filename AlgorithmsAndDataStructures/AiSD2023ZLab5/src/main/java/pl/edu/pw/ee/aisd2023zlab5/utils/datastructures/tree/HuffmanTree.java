package pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RbtMap;

public class HuffmanTree {

    private NodeHuffman root;
    private RbtMap<Byte, String> dictionaryForCompression;
    private RbtMap<String, Byte> dictionaryForDecompression;

    public HuffmanTree(NodeHuffman root) {
        if (root == null) {
            throw new HuffmanException("Root can't be null!", HuffmanTree.class.getName(),
                    "HuffmanTree(NodeHuffman root)", IllegalArgumentException.class.getName());
        }
        this.root = root;
    }

    public void createDictionaries() {
        dictionaryForCompression = new RbtMap<>();
        dictionaryForDecompression = new RbtMap<>();

        String result;
        if (root.getLeft() == null && root.getRight() == null) {
            result = "1";
            dictionaryForCompression.setValue(root.getSymbol(), result);
            dictionaryForDecompression.setValue(result, root.getSymbol());
        } else {
            result = "";
            addEntries(root, result);
        }
    }

    public TreePreorderResult getPreorderResult() {
        return getPreorderResult(root);
    }

    public RbtMap<Byte, String> getDictionaryForCompression() {
        return dictionaryForCompression;
    }

    public RbtMap<String, Byte> getDictionaryForDecompression() {
        return dictionaryForDecompression;
    }

    private void addEntries(NodeHuffman head, String result) {
        if (head.getLeft() == null && head.getRight() == null) {
            dictionaryForCompression.setValue(head.getSymbol(), result);
            dictionaryForDecompression.setValue(result, head.getSymbol());
            return;
        }
        addEntries(head.getLeft(), result + "0");
        addEntries(head.getRight(), result + "1");
    }

    private TreePreorderResult getPreorderResult(NodeHuffman root) {
        if (root == null) {
            throw new HuffmanException("Root of the HuffmanTree is null!", HuffmanTree.class.getName(),
                    "getPreorderResult(NodeHuffman root)", IllegalArgumentException.class.getName());
        }

        TreePreorderResult preorderResult = new TreePreorderResult();
        if (root.getLeft() == null && root.getRight() == null) {
            handleOneNodeTraversal(root, preorderResult);
        } else {
            handleRegularTraversal(root, preorderResult);
        }
        return preorderResult;
    }

    private void handleOneNodeTraversal(NodeHuffman root, TreePreorderResult preorderResult) {
        byte currentByte = 0;
        int bitsLeftToWholeByte = 8;
        currentByte <<= 1;
        currentByte += 1;
        bitsLeftToWholeByte--;
        byte currentSymbol = root.getSymbol().byteValue();
        for (int i = 0; i < 8; i++) {
            if (bitsLeftToWholeByte == 0) {
                preorderResult.add(currentByte);
                currentByte = 0;
                bitsLeftToWholeByte = 8;
            }

            if ((currentSymbol & 0b10000000) == 0) {
                currentByte <<= 1;
            } else {
                currentByte <<= 1;
                currentByte += 1;
            }
            bitsLeftToWholeByte--;
            currentSymbol <<= 1;
        }
        preorderResult.add(currentByte);
        preorderResult.setImportantBitsInTheLastByte(1);
    }

    private void handleRegularTraversal(NodeHuffman root, TreePreorderResult preorderResult) {
        byte currentByte = 0;
        int bitsLeftToWholeByte = 8;

        List<NodeHuffman> nodeStack = new ArrayList<>();
        nodeStack.add(root);
        int n = 1;

        while (n > 0) {
            n--;
            NodeHuffman head = nodeStack.remove(n);

            if (bitsLeftToWholeByte == 0) {
                preorderResult.add(currentByte);
                currentByte = 0;
                bitsLeftToWholeByte = 8;
            }

            if (head.getSymbol() == null) {
                currentByte <<= 1;
                bitsLeftToWholeByte--;
            } else {
                currentByte <<= 1;
                currentByte += 1;
                bitsLeftToWholeByte--;

                byte currentSymbol = head.getSymbol().byteValue();
                for (int i = 0; i < 8; i++) {
                    if (bitsLeftToWholeByte == 0) {
                        preorderResult.add(currentByte);
                        currentByte = 0;
                        bitsLeftToWholeByte = 8;
                    }

                    if ((currentSymbol & 0b10000000) == 0) {
                        currentByte <<= 1;
                    } else {
                        currentByte <<= 1;
                        currentByte += 1;
                    }
                    bitsLeftToWholeByte--;
                    currentSymbol <<= 1;
                }
            }

            if (head.getRight() != null) {
                nodeStack.add(head.getRight());
                n++;
            }

            if (head.getLeft() != null) {
                nodeStack.add(head.getLeft());
                n++;
            }
        }

        if (bitsLeftToWholeByte > 0 && bitsLeftToWholeByte < 8) {
            preorderResult.add(currentByte);
            preorderResult.setImportantBitsInTheLastByte(8 - bitsLeftToWholeByte);
        } else {
            preorderResult.add(currentByte);
            preorderResult.setImportantBitsInTheLastByte(8);
        }
    }

}
