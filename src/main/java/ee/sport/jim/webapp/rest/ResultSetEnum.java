package ee.sport.jim.webapp.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultSetEnum {
	DEFAULT_PAGE_RESULT_LIMIT(25),
	DEFAULT_PAGE_NUMBER(1);

	private final int value;
}
