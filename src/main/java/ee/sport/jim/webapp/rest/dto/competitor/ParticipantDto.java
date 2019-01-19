package ee.sport.jim.webapp.rest.dto.competitor;

import ee.sport.jim.webapp.domain.shared.Gender;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDto {
	@NotNull
	private Long participantId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Integer competitorNumber;

	@NotNull
	private LocalDateTime dateOfBirth;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String phoneNumber;
	private String sportsClub;

	@NotNull
	private Gender gender;
	private CompetitionDistanceDto distance;
	private Integer participationCount;

	@NotNull
	private boolean paymentFulfilled;
	private boolean championshipParticipation;

	@NotNull
	private boolean publishData;

	@NotNull
	private boolean newsletterSubscription;

	@NotNull
	private boolean numberPrinted;

	@NotNull
	private boolean envelopePrinted;
	private Long chipId;
	private Long distanceId;
}
