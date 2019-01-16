package ee.sport.jim.webapp.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
	MALE("Mees"),
	FEMALE("Naine");

	private String genderResult;
}
