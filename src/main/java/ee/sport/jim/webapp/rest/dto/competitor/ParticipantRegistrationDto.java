package ee.sport.jim.webapp.rest.dto.competitor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompParticipantRegistrationDto {
	@NotNull
	@Max(value = 100)
	private String firstName;
	@NotNull
	@Max(value = 100)
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String gender;
	private String phoneNumber;
	@NotNull
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	private Integer participationCount;
	@NotNull
	private boolean newsletterSubscription;
	@NotNull
	private boolean publishData;
	@NotNull
	private Long competitionDistanceId;
}
