package pl.edu.pw.ee.aisd2023zlab5.utils.validation;

public final class ParametersValidator {

    private ParametersValidator() {
    }

    public static boolean validateInputAndChooseMode(String[] args) {
        if (args == null || args.length == 0) {
            throw new HuffmanException("FilePath, OutfilePath and mode need to be provided!", ParametersValidator.class.getName(),
                    "validateAndChooseMode(String[] args)", IllegalArgumentException.class.getName());
        }

        if (args.length < 3) {
            throw new HuffmanException("All three parameters need to be provided!", ParametersValidator.class.getName(),
                    "validateAndChooseMode(String[] args)", IllegalArgumentException.class.getName());
        }

        if (args[0].isBlank() || args[1].isBlank() || args[2].isBlank()) {
            throw new HuffmanException("The parameters cannot be blank!", ParametersValidator.class.getName(),
                    "validateAndChooseMode(String[] args)", IllegalArgumentException.class.getName());
        }

        return shouldCompress(args[2]);
    }

    private static boolean shouldCompress(String argument) {
        boolean shouldCompress;

        if (argument.toUpperCase().equals("C")) {
            shouldCompress = true;
        } else if (argument.toUpperCase().equals("D")) {
            shouldCompress = false;
        } else {
            throw new HuffmanException("Wrong mode provided! Use C for compression or D for decompression", ParametersValidator.class.getName(),
                    "shouldCompress(String argument)", IllegalArgumentException.class.getName());
        }

        return shouldCompress;
    }
}
