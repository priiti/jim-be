package ee.sport.jim.webapp.rest.dto.competition;

import ee.sport.jim.system.core.BooleanHolder;
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
	private BooleanHolder paymentFulfilled;
	private BooleanHolder publishData;
	private BooleanHolder newsletterSubscription;
	private BooleanHolder numberPrinted;
	private BooleanHolder envelopePrinted;
}
