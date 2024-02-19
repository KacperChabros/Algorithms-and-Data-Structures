package pl.edu.pw.ee.aisd2023zlab5.testutils;

import java.io.File;

public final class FileRemover {

    private FileRemover() {
    }

    public static void removeFile(String path) {
        File file = new File(path);
        file.delete();
    }
}
