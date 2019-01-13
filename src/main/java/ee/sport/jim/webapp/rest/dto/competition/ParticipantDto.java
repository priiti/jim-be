package ee.sport.jim.webapp.rest.dto.competition;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ee.sport.jim.system.core.BooleanHolder;
import ee.sport.jim.webapp.domain.shared.Gender;
import ee.sport.jim.webapp.rest.util.GenderSerializer;
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
	@JsonSerialize(using = GenderSerializer.class)
	private Gender gender;
	private CompetitionDistanceDto distance;
	private Integer participationCount;
	private BooleanHolder paymentFulfilled;
	private BooleanHolder championshipParticipation;
	private BooleanHolder publishData;
	private BooleanHolder newsletterSubscription;
	private BooleanHolder numberPrinted;
	private BooleanHolder envelopePrinted;
}
