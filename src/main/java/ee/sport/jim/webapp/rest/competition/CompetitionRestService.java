package ee.sport.jim.webapp.rest.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;

import java.util.List;

public interface CompetitionRestService {

	List<CompetitionDto> getCompetitions();

	CompetitionDto getCompetition(long competitionId);

}
