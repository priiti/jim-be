package ee.sport.jim.webapp.service;

import ee.sport.jim.webapp.domain.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

	List<Competition> getCompetitions();

	Optional<Competition> getCompetition(long competitionId);

}
