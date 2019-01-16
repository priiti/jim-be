package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.http.ResponseEntity;

public interface CompetitorRestService {

	ResponseEntity<?> register(ParticipantRegistrationDto participantRegistrationDto);

	ResponseEntity<?> updateParticipantPayment(long participantId);

	ResponseEntity<?> removeParticipant(long participantId);

	ResponseEntity<?> updateCompetitorParticipant(ParticipantDto participantDto);

}
