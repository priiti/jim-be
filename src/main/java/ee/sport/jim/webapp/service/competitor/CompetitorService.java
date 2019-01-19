package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.CompetitorParticipantHolder;

public interface CompetitorService {

	Participant register(CompetitorParticipantHolder participantHolder, long distanceId, long competitionId);

	Participant updateParticipantPayment(Long participantId);

	Participant updateCompetitorParticipant(CompetitorParticipantHolder participantHolder);

	void deleteParticipant(long participantId);
}
