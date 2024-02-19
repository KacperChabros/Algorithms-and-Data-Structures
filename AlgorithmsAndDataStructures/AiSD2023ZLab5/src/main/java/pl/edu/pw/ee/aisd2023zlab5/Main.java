package pl.edu.pw.ee.aisd2023zlab5;

import pl.edu.pw.ee.aisd2023zlab5.utils.validation.ParametersValidator;
import pl.edu.pw.ee.aisd2023zlab5.utils.validation.HuffmanException;

public class Main {

    public static void main(String[] args) {
        Boolean shouldCompress = null;
        try {
            shouldCompress = ParametersValidator.validateInputAndChooseMode(args);
        } catch (HuffmanException ex) {
            displayHelp(ex);
        }

        if (shouldCompress == null) {
            return;
        }
        try {
            String filePath = args[0];
            String outfilePath = args[1];
            if (shouldCompress) {
                HuffmanCompressor.compress(filePath, outfilePath);
            } else {
                HuffmanCompressor.decompress(filePath, outfilePath);
            }
        } catch (HuffmanException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void displayHelp(Exception ex) {
        String message = "Error message:\n";
        message += ex.getMessage() + "\n\n";
        message += "HELP\n";
        message += "-------------------------------------\n";
        message += "Correct usage: java Main.java [filePath] [outfilePath] [mode]\n";
        message += "Parameters:\n";
        message += "filePath: absolute path to file\n";
        message += "outfilePath: absolute path to where the outfile should be saved\n";
        message += "mode: C or D\n";
        message += "C - compress;\tD - decompress\n";
        message += "Kind request to try again";
        System.out.println(message);
    }
}
