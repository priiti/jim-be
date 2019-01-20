package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.CompetitorParticipantHolder;

import java.util.Optional;

public interface CompetitorService {

	Optional<Participant> findById(long participantId);

	Participant register(CompetitorParticipantHolder registrationHolder);

	Participant updateParticipantPayment(long participantId);

	Participant updateParticipant(Participant participant);

	Participant save(Participant participant);

	void deleteParticipant(long participantId);

	boolean ifParticipantExistsBy(long id);
}
