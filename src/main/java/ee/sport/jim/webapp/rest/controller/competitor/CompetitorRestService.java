package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;

public interface CompetitorRestService {

	void register(ParticipantRegistrationDto participantRegistrationDto);

}
