package design.patterns.virtual.file;

public class FileHandlingException extends RuntimeException {

    FileHandlingException(Exception exception) {
        super(exception);
    }
}
