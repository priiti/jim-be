package ee.sport.jim.webapp.rest.dto.competitor;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParticipantDto {
	private Long id;
	private Integer participationCount;
	private boolean championshipParticipation;
	private boolean paymentFulfilled;
	private boolean numberPrinted;
	private boolean envelopePrinted;
	private Integer competitorNumber;
	private Long chipId;
	private CompetitorDto competitor;
	private CompetitionDistanceDto competitionDistance;
}
