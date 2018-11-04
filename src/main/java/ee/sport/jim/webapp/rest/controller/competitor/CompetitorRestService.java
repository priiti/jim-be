package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.CompParticipantRegistrationDto;

public interface CompetitorRestService {

	void register(CompParticipantRegistrationDto compParticipantRegistrationDto);

}
