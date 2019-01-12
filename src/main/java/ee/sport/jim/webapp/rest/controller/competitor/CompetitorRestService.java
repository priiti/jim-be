package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.http.ResponseEntity;

public interface CompetitorRestService {

	void register(ParticipantRegistrationDto participantRegistrationDto);

	ResponseEntity<?> updateParticipantPaymentInfo(Long participantId);

}
