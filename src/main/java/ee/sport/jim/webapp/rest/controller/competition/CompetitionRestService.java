package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.PagedResponse;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantDto;

public interface CompetitionRestService {

	CompetitionDto getCompetitionForRegistration(long competitionId);

	PagedResponse<ParticipantDto> getPaidCompParticipants(long distanceId, long competitionId, int pageNumber, int limit);

	PagedResponse<ParticipantDto> getAllCompParticipants(long distanceId, long competitionId, int pageNumber, int limit);

	CompDistanceInfoDto getCompetitionDistanceInfo(long competitionId);
}
