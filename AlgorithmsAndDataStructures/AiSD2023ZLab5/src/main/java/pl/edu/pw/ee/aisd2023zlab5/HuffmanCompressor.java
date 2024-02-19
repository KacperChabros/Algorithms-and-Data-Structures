package pl.edu.pw.ee.aisd2023zlab5;

import java.util.List;
import java.util.ArrayList;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RbtMap;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import pl.edu.pw.ee.aisd2023zlab5.utils.filehandling.FileWriter;
import pl.edu.pw.ee.aisd2023zlab5.utils.filehandling.FileReader;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.HuffmanTree;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.TreePreorderResult;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.RecreatedTreeResult;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.NodeHuffman;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.queue.HeapMin;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.queue.PriorityQueue;

public final class HuffmanCompressor {

    private HuffmanCompressor() {
    }

    public static void compress(String filePath, String outfilePath) {
        validatePaths(filePath, outfilePath);
        byte[] fileAsBytes = FileReader.getFileAsBytes(filePath);
        validateFileForCompression(fileAsBytes);
        HuffmanTree tree = createTree(fileAsBytes);
        tree.createDictionaries();
        RbtMap<Byte, String> dictionary = tree.getDictionaryForCompression();
        TreePreorderResult treeAsBytes = tree.getPreorderResult();
        FileWriter.writeCodesToFile(outfilePath, fileAsBytes, treeAsBytes, dictionary);
    }

    public static void decompress(String filePath, String outfilePath) {
        validatePaths(filePath, outfilePath);
        byte[] fileAsBytes = FileReader.getFileAsBytes(filePath);
        validateFileForDecompression(fileAsBytes);
        RecreatedTreeResult recreatedResult = recreateTree(fileAsBytes);
        HuffmanTree tree = recreatedResult.getTree();
        tree.createDictionaries();
        RbtMap<String, Byte> dictionary = tree.getDictionaryForDecompression();
        FileWriter.writeDecodedFile(outfilePath, fileAsBytes, recreatedResult, dictionary);
    }

    private static void validatePaths(String filePath, String outfilePath) {
        if (filePath.isBlank() || outfilePath.isBlank()) {
            throw new HuffmanException("filePath and outfilePath can't be null or whitespace", HuffmanCompressor.class.getName(),
                    "validatePaths(String filePath, String outfilePath)", IllegalArgumentException.class.getName());
        }
    }

    private static void validateFileForCompression(byte[] fileAsBytes) {
        if (fileAsBytes == null || fileAsBytes.length == 0) {
            throw new HuffmanException("Cannot compress an empty file", HuffmanCompressor.class.getName(),
                    "validateFileForCompression(byte[] fileAsBytes)", IllegalArgumentException.class.getName());
        }
    }

    private static HuffmanTree createTree(byte[] fileAsBytes) {
        int[] counters = countSymbols(fileAsBytes);
        PriorityQueue<NodeHuffman> queue = createPriorityQueue(counters);
        HuffmanTree tree = createHuffmanTree(queue);
        return tree;
    }

    private static int[] countSymbols(byte[] fileAsBytes) {
        int[] counters = initializeCounters();

        for (int i = 0; i < fileAsBytes.length; i++) {
            byte currentSymbol = fileAsBytes[i];
            if (currentSymbol < 0) {
                counters[currentSymbol + 256]++;
            } else {
                counters[currentSymbol]++;
            }
        }

        return counters;
    }

    private static int[] initializeCounters() {
        int counters[] = new int[256];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = 0;
        }
        return counters;
    }

    private static PriorityQueue<NodeHuffman> createPriorityQueue(int[] counters) {
        PriorityQueue<NodeHuffman> queue = new HeapMin<>();
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] != 0) {
                queue.put(new NodeHuffman((byte) i, counters[i], null, null));
            }
        }

        return queue;
    }

    private static HuffmanTree createHuffmanTree(PriorityQueue<NodeHuffman> queue) {
        while (queue.getSize() > 1) {
            NodeHuffman min = queue.pop();
            NodeHuffman secondMin = queue.pop();
            queue.put(new NodeHuffman(null, min.getFrequency() + secondMin.getFrequency(), min, secondMin));
        }
        return new HuffmanTree(queue.pop());
    }

    private static void validateFileForDecompression(byte[] fileAsBytes) {
        if (fileAsBytes == null || fileAsBytes.length < 5) {
            throw new HuffmanException("The file is too short to have been compressed by this compressor", HuffmanCompressor.class.getName(),
                    "validateFileForDecompression(byte[] fileAsBytes)", IllegalArgumentException.class.getName());
        }
        byte codeOfX = 88;
        byte codeOfA = 65;
        if (fileAsBytes[0] != codeOfX || fileAsBytes[1] != codeOfA) {
            throw new HuffmanException("This file hasn't been compressed by this compressor! (or is corrupted)", HuffmanCompressor.class.getName(),
                    "validateFileForDecompression(byte[] fileAsBytes)", IllegalArgumentException.class.getName());
        }
        byte expectedChecksum = (byte) 0b10111010;
        byte currentChecksum = fileAsBytes[2];

        for (int i = 3; i < fileAsBytes.length; i++) {
            currentChecksum ^= fileAsBytes[i];
        }

        if (currentChecksum != expectedChecksum) {
            throw new HuffmanException("This file is corrupted!", HuffmanCompressor.class.getName(),
                    "validateFileForDecompression(byte[] fileAsBytes)", IllegalArgumentException.class.getName());
        }
    }

    private static RecreatedTreeResult recreateTree(byte[] fileAsBytes) {
        byte currentReadByte;
        byte currentSymbol = 0;
        boolean shouldContinueReading = true;
        boolean isSymbol = false;
        int bitsLeftToWholeSymbol = 8;
        int bitsLeftInLastByteOfTree = 0;
        int lastByteOfTree = 5;
        List<NodeHuffman> nodeStack = new ArrayList<>();
        NodeHuffman root = new NodeHuffman(null, null, null, null);
        nodeStack.add(root);
        int n = 1;
        NodeHuffman currentNode = null;
        for (int i = 4; i < fileAsBytes.length; i++) {
            if (!shouldContinueReading) {
                lastByteOfTree = i - 1;
                break;
            }
            currentReadByte = fileAsBytes[i];
            for (int j = 0; j < 8; j++) {
                if (!isSymbol && n <= 0) {
                    shouldContinueReading = false;
                    bitsLeftInLastByteOfTree = 8 - j;
                    break;
                }

                if (!isSymbol) {
                    n--;
                    currentNode = nodeStack.remove(n);
                    if ((currentReadByte & 0b10000000) == 0) {
                        NodeHuffman leftNode = new NodeHuffman(null, null, null, null);
                        NodeHuffman rightNode = new NodeHuffman(null, null, null, null);
                        currentNode.setLeft(leftNode);
                        currentNode.setRight(rightNode);
                        nodeStack.add(rightNode);
                        n++;
                        nodeStack.add(leftNode);
                        n++;
                    } else {
                        isSymbol = true;
                    }
                } else {
                    if ((currentReadByte & 0b10000000) == 0) {
                        currentSymbol <<= 1;
                    } else {
                        currentSymbol <<= 1;
                        currentSymbol += 1;
                    }
                    bitsLeftToWholeSymbol--;
                    if (bitsLeftToWholeSymbol <= 0) {
                        currentNode.setSymbol(currentSymbol);
                        currentSymbol = 0;
                        bitsLeftToWholeSymbol = 8;
                        isSymbol = false;
                    }
                }
                currentReadByte <<= 1;
            }
        }
        HuffmanTree tree = new HuffmanTree(root);
        RecreatedTreeResult recreateResult = new RecreatedTreeResult(tree, lastByteOfTree, bitsLeftInLastByteOfTree);

        return recreateResult;
    }
}
