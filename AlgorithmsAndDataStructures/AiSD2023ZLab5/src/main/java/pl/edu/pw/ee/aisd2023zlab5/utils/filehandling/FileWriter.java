package pl.edu.pw.ee.aisd2023zlab5.utils.filehandling;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.RecreatedTreeResult;
import pl.edu.pw.ee.aisd2023zlab5.utils.datastructures.tree.TreePreorderResult;
import pl.edu.pw.ee.aisd2023zlab5.aisd2023zlab4.RbtMap;

public final class FileWriter {

    private FileWriter() {
    }

    public static void writeCodesToFile(String outfilePath, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary) {
        validateParameters(outfilePath, fileAsBytes, dictionary);
        validatePreorder(preorder);
        File newFile = new File(outfilePath);
        if (newFile.exists()) {
            throw new HuffmanException("The file with the given name already exists!", FileWriter.class.getName(),
                    "writeCodesToFile(String outfilePath, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary)", 
                    IllegalArgumentException.class.getName());
        }

        try {
            newFile.createNewFile();
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(newFile, "rw")) {
                compressFile(randomAccessFile, fileAsBytes, preorder, dictionary);
            }

        } catch (FileNotFoundException ex) {
            throw new HuffmanException("The file for random access file was not found!", FileWriter.class.getName(),
                    "writeCodesToFile(String outfilePath, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary)", ex.getClass().getName());
        } catch (IOException ex) {
            throw new HuffmanException("Couldn't create the file!", FileWriter.class.getName(),
                    "writeCodesToFile(String outfilePath, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary)", ex.getClass().getName());
        }
    }

    public static void writeDecodedFile(String outfilePath, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary) {
        validateParameters(outfilePath, fileAsBytes, dictionary);
        validateRecreatedTree(recreatedResult);
        File newFile = new File(outfilePath);
        if (newFile.exists()) {
            throw new HuffmanException("The file with the given name already exists!", FileWriter.class.getName(),
                    "writeDecodedFile(String outfilePath, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary)", 
                    IllegalArgumentException.class.getName());
        }

        try {
            newFile.createNewFile();
            decompressFile(newFile, fileAsBytes, recreatedResult, dictionary);
        } catch (IOException ex) {
            throw new HuffmanException("Couldn't create the File!", FileWriter.class.getName(),
                    "writeDecodedFile(String outfilePath, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary)", 
                    ex.getClass().getName());
        }
    }

    private static void validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary) {
        if (filePath == null || filePath.isBlank()) {
            throw new HuffmanException("FilePath can't be null nor whitespace", FileWriter.class.getName(),
                    "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)",
                    IllegalArgumentException.class.getName());
        }

        if (fileAsBytes == null || fileAsBytes.length == 0) {
            throw new HuffmanException("fileAsBytes can't be null nor empty", FileWriter.class.getName(),
                    "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)",
                    IllegalArgumentException.class.getName());
        }

        if (dictionary == null) {
            throw new HuffmanException("dictionary can't be null", FileWriter.class.getName(),
                    "validateParameters(String filePath, byte[] fileAsBytes, RbtMap<?, ?> dictionary)",
                    IllegalArgumentException.class.getName());
        }
    }

    private static void validatePreorder(TreePreorderResult preorder) {
        if (preorder == null || preorder.getTreePreorder() == null || preorder.getTreePreorder().isEmpty()) {
            throw new HuffmanException("preorder can't be null nor empty", FileWriter.class.getName(),
                    "validatePreorder(TreePreorderResult preorder)", IllegalArgumentException.class.getName());
        }
    }

    private static void validateRecreatedTree(RecreatedTreeResult recreatedTree) {
        if (recreatedTree == null || recreatedTree.getTree() == null) {
            throw new HuffmanException("recreatedTree can't be null nor empty", FileWriter.class.getName(),
                    "validateRecreatedTree(RecreatedTreeResult recreatedTree)", IllegalArgumentException.class.getName());
        }
    }

    private static void compressFile(RandomAccessFile file, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary) {
        writeHeader(file);
        writeTree(file, preorder);
        writeFileContents(file, fileAsBytes, preorder, dictionary);
        writeChecksum(file);
    }

    private static void writeHeader(RandomAccessFile file) {
        try {
            file.write('X');
            file.write('A');
            file.seek(4);
        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem with writing header to the file", FileWriter.class.getName(),
                    "writeHeader(RandomAccessFile file)", ex.getClass().getName());
        }
    }

    private static void writeTree(RandomAccessFile file, TreePreorderResult preorder) {
        try {
            List<Byte> preorderAsBytes = preorder.getTreePreorder();
            for (int i = 0; i < preorderAsBytes.size() - 1; i++) {
                file.write(preorderAsBytes.get(i));
            }
        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem with writing tree to the file", FileWriter.class.getName(),
                    "writeTree(RandomAccessFile file, TreePreorderResult preorder)", ex.getClass().getName());
        }
    }

    private static void writeFileContents(RandomAccessFile file, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary) {
        List<Byte> preorderAsBytes = preorder.getTreePreorder();
        byte lastByteOfTree = preorderAsBytes.get(preorderAsBytes.size() - 1);

        byte currentByte = lastByteOfTree;
        int bitsLeftToWholeByte = 8 - preorder.getImportantBitsInTheLastByte();
        try {
            for (int i = 0; i < fileAsBytes.length; i++) {
                byte currentSymbol = fileAsBytes[i];
                String code = dictionary.getValue(currentSymbol);
                for (int j = 0; j < code.length(); j++) {
                    if (bitsLeftToWholeByte == 0) {
                        file.write(currentByte);
                        currentByte = 0;
                        bitsLeftToWholeByte = 8;
                    }

                    if (code.charAt(j) == '0') {
                        currentByte <<= 1;
                    } else {
                        currentByte <<= 1;
                        currentByte += 1;
                    }
                    bitsLeftToWholeByte--;
                    currentSymbol <<= 1;
                }

            }
            int importantBitsInTheLastByte;
            if (bitsLeftToWholeByte > 0 && bitsLeftToWholeByte < 8) {
                currentByte <<= bitsLeftToWholeByte;
                file.write(currentByte);
                importantBitsInTheLastByte = 8 - bitsLeftToWholeByte;
            } else {
                file.write(currentByte);
                importantBitsInTheLastByte = 8;
            }
            writeNumberOfImportantBitsOfLastByte(file, importantBitsInTheLastByte);

        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem with writing file contents", FileWriter.class.getName(),
                    "writeFileContents(RandomAccessFile file, byte[] fileAsBytes, TreePreorderResult preorder, RbtMap<Byte, String> dictionary)", ex.getClass().getName());
        }
    }

    private static void writeNumberOfImportantBitsOfLastByte(RandomAccessFile file, int importantBitsInTheLastByte) {
        try {
            file.seek(3);
            file.writeByte(importantBitsInTheLastByte);
        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem with writing number of important bits of last byte", FileWriter.class.getName(),
                    "writeNumberOfImportantBitsOfLastByte(RandomAccessFile file, int importantBitsInTheLastByte)", ex.getClass().getName());
        }
    }

    private static void writeChecksum(RandomAccessFile file) {
        try {
            byte checksum = (byte) 0b10111010;
            int currentRead;
            file.seek(3);
            while ((currentRead = file.read()) != -1) {
                checksum ^= (byte) currentRead;
            }
            file.seek(2);
            file.write(checksum);
        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem with calculating or writing checksum", FileWriter.class.getName(),
                    "writeChecksum(RandomAccessFile file)", ex.getClass().getName());
        }
    }

    private static void decompressFile(File file, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary) {
        int importantBitsOfLastByte = fileAsBytes[3];
        int currentImportantBits = recreatedResult.getBitsLeftInLastByteOfTree();
        int lastByteOfTree = recreatedResult.getLastByteOfTree();
        int whereToStart;

        if (currentImportantBits > 0 && currentImportantBits < 8) {
            whereToStart = lastByteOfTree;
            fileAsBytes[whereToStart] <<= 8 - currentImportantBits;
        } else {
            whereToStart = lastByteOfTree + 1;
            currentImportantBits = 8;
        }

        String currentCode = "";
        Byte foundValue = null;
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = whereToStart; i < fileAsBytes.length; i++) {
                if (i == fileAsBytes.length - 1) {
                    currentImportantBits = importantBitsOfLastByte;
                }

                byte currentByte = fileAsBytes[i];

                for (int j = 0; j < currentImportantBits; j++) {
                    if ((currentByte & 0b10000000) == 0) {
                        currentByte <<= 1;
                        currentCode += "0";
                    } else {
                        currentByte <<= 1;
                        currentCode += "1";
                    }
                    if ((foundValue = dictionary.getValue(currentCode)) != null) {
                        out.write(foundValue);
                        currentCode = "";
                    }
                }
                currentImportantBits = 8;
            }
        } catch (FileNotFoundException ex) {
            throw new HuffmanException("The file to write to wasn't found", FileWriter.class.getName(),
                    "decompressFile(File file, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary)", ex.getClass().getName());
        } catch (IOException ex) {
            throw new HuffmanException("There has been a problem while writing decoded content to the file", FileWriter.class.getName(),
                    "decompressFile(File file, byte[] fileAsBytes, RecreatedTreeResult recreatedResult, RbtMap<String, Byte> dictionary)", ex.getClass().getName());
        }
    }
}
