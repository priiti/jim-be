package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;

import java.util.Optional;

public interface CompetitionService {

	Optional<Competition> getCompetitionForRegistration(long competitionId);

	Optional<CompetitionDistance> getCompetitionDistance(long competitionDistanceId);

}
