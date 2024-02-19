package pl.edu.pw.ee.aisd2023zlab5.utils.filehandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;

public final class FileReader {

    private FileReader() {
    }

    public static byte[] getFileAsBytes(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new HuffmanException("This file doesn't exist!", FileReader.class.getName(),
                    "getFileAsBytes(String filePath)", IllegalArgumentException.class.getName());
        }

        byte[] fileAsBytes = null;
        try {
            fileAsBytes = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            throw new HuffmanException("An error occurred reading a file!", FileReader.class.getName(),
                    "getFileAsBytes(String filePath)", ex.getClass().getName());
        }

        return fileAsBytes;
    }
}
