package pl.edu.pw.ee.aisd2023zlab5.utils.validation;

public class HuffmanException extends RuntimeException {

    private final String methodName;
    private final String className;
    private final String originalExceptionClassName;
    private String message;

    public HuffmanException(String originalMessage, String className, String methodName, String originalExceptionClassName) {
        super();
        this.className = className;
        this.methodName = methodName;
        this.originalExceptionClassName = originalExceptionClassName;
        createMessage(originalMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    private void createMessage(String originalMessage) {
        message = "An ERROR occurred!\n";
        message += "There was an error in the process of (de)compressing the file\n";
        message += "Class that caused the error: " + className + "\n";
        message += "Method that caused the error: " + methodName + "\n";
        message += "Original exception class: " + originalExceptionClassName + "\n";
        message += "Original message: " + originalMessage + "\n";

    }

}
