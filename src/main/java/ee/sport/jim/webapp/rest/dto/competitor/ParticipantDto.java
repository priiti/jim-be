package ee.sport.jim.webapp.rest.dto.competitor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder(builderMethodName = "builder")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
	fieldVisibility = JsonAutoDetect.Visibility.ANY,
	creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class ParticipantDto {
	private Long id;
	private Integer participationCount;
	private boolean championshipParticipation;
	@NotNull
	private boolean paymentFulfilled;
	@NotNull
	private boolean numberPrinted;
	@NotNull
	private boolean envelopePrinted;
	private Integer competitorNumber;
	private Long chipId;
	private CompetitorDto competitor;
	private CompetitionDistanceDto competitionDistance;
}




