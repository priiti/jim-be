package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantsInfoDto;

public interface CompetitionRestService {

	CompetitionDto getCompetitionForRegistration(long competitionId);

	ParticipantsInfoDto getPaidCompParticipants(long distanceId, long competitionId, Integer pageNumber, Integer limit);

	ParticipantsInfoDto getAllCompParticipants(long distanceId, long competitionId, Integer pageNumber, Integer limit);

	CompDistanceInfoDto getCompetitionDistanceInfo(long competitionId);
}
