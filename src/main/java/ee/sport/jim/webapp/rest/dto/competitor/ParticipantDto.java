package ee.sport.jim.webapp.rest.dto.competitor;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ParticipantDto {
	@NotNull
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
