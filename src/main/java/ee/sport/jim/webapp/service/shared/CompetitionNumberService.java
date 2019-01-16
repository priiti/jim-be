package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;

public interface CompetitionNumberService {

	int generateCompetitorNumber(CompetitionDistance competitionDistance, Participant participant);

	int getDistanceStartingNumber(CompetitionDistance competitionDistance);

}
