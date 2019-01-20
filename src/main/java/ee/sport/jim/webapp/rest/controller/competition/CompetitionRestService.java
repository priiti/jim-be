package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.PagedResponse;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;

public interface CompetitionRestService {

	CompetitionDto getCompetitionForRegistration(long competitionId);

	PagedResponse<ParticipantDto> getPublicParticipants(long distanceId, long competitionId, int pageNumber, int limit);

	PagedResponse<ParticipantDto> getPrivateParticipants(long distanceId, long competitionId, int pageNumber, int limit);

	CompetitionDto getCompetitionInformation(long competitionId);

}
