package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompetitionService extends CrudService<Competition, Long> {

	Optional<CompetitionDistance> getCompetitionDistance(long distanceId, long competitionId);

	Optional<CompetitionDistance> getCompetitionDistance(long distanceId);

	Page<Participant> getPaidCompetitionParticipants(long distanceId, Pageable page);

	Page<Participant> getAllCompetitionParticipants(long distanceId, Pageable page);

	boolean existsById(long competitionId);

}
