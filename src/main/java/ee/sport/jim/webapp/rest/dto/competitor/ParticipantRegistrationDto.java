package ee.sport.jim.webapp.rest.dto.competitor;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ee.sport.jim.webapp.domain.shared.Gender;
import ee.sport.jim.webapp.rest.util.GenderSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantRegistrationDto {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	@JsonSerialize(using = GenderSerializer.class)
	private Gender gender;
	private String phoneNumber;
	@NotNull
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	private Integer participationCount;
	private boolean championshipParticipation;
	@NotNull
	private boolean newsletterSubscription;
	@NotNull
	private boolean publishData;
	@NotNull
	private Long competitionDistanceId;
	private Long competitionId;
}
