package ee.sport.jim.webapp.rest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class ApiError {
	private HttpStatus status;
	private String message;
	private Map<String, String> errors;

	public ApiError(HttpStatus status, String message, Map<String, String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
}
