package PiMongo;

public class ExitProgramException extends Exception {
	public ExitProgramException(String message) {
        super(message);
    }
	public ExitProgramException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
