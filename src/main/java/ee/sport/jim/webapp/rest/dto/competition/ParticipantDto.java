package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDto {
	private Long participantId;
	private Integer competitorNumber;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	private CompetitionDistanceDto distance;
	private Integer participationCount;
	private boolean paymentFulfilled;
	private boolean publishData;
	private boolean newsletterSubscription;
	private boolean numberPrinted;
	private boolean envelopePrinted;
}
