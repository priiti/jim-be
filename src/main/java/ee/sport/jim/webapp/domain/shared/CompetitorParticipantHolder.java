package ee.sport.jim.webapp.domain.shared;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitorParticipantHolder {
	private Competitor competitor;
	private Participant participant;
	private Long competitionDistanceId;
	private Long competitionId;
}
