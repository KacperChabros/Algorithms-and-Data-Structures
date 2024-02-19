package pl.edu.pw.ee.aisd2023zlab5.testutils;

public final class ErrorMessageProvider {

    private ErrorMessageProvider() {
    }

    public static String createMessage(String originalMessage, String className, String methodName, String originalExceptionClassName) {
        String message = "An ERROR occurred!\n";
        message += "There was an error in the process of (de)compressing the file\n";
        message += "Class that caused the error: " + className + "\n";
        message += "Method that caused the error: " + methodName + "\n";
        message += "Original exception class: " + originalExceptionClassName + "\n";
        message += "Original message: " + originalMessage + "\n";
        return message;
    }

}
