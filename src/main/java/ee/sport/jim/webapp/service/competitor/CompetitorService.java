package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.RegistrationHolder;

import java.util.Optional;

public interface CompetitorService {

	Participant register(RegistrationHolder registrationHolder, long distanceId, long competitionId);

	Optional<Participant> updateParticipantPaymentInfo(Long participantId);

}
