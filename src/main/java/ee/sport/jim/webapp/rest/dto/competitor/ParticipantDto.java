package ee.sport.jim.webapp.rest.dto.competitor;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ee.sport.jim.system.core.BooleanHolder;
import ee.sport.jim.webapp.domain.shared.Gender;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import ee.sport.jim.webapp.rest.util.BooleanSerializer;
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
	private String phoneNumber;
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	@JsonSerialize(using = GenderSerializer.class)
	private Gender gender;
	private CompetitionDistanceDto distance;
	private Integer participationCount;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder paymentFulfilled;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder championshipParticipation;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder publishData;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder newsletterSubscription;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder numberPrinted;
	@JsonSerialize(using = BooleanSerializer.class)
	private BooleanHolder envelopePrinted;
	private Long chipId;
	private Long distanceId;
}
