package ee.sport.jim.webapp.rest.dto.competitor;

import ee.sport.jim.webapp.domain.shared.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CompetitorDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Gender gender;
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	private boolean newsLetterSubscription;
	private boolean publishData;
}
