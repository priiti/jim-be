package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;

public interface CompetitionRestService {

	CompetitionDto getCompetitionForRegistration(long competitionId);

}
