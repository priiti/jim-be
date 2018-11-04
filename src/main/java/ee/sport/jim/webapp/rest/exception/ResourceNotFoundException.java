package ee.sport.jim.webapp.rest.exception;

public class DistanceNotFoundException extends RuntimeException {
	public DistanceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
