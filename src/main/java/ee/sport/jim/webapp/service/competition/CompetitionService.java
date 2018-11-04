package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.service.CrudService;

import java.util.Optional;

public interface CompetitionService extends CrudService<Competition, Long> {

	Optional<CompetitionDistance> getCompetitionDistance(long competitionDistanceId);

}
