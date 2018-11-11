package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;

public interface CompetitionRestService {

	CompetitionDto getCompetitionForRegistration(long competitionId);

	CompParticipantInfoDto getCompetitionParticipants(long competitionId);

}
