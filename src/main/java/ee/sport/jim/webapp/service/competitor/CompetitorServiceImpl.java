package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.CompetitorParticipantHolder;
import ee.sport.jim.webapp.repository.CompetitorRepository;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import ee.sport.jim.webapp.service.shared.CompetitionNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static ee.sport.jim.webapp.service.CompetitionUtils.getCompetitorFullName;

@Slf4j
@Service
public class CompetitorServiceImpl implements CompetitorService {
	private final ParticipantRepository participantRepository;
	private final CompetitorRepository competitorRepository;
	private final CompetitionService competitionService;
	private final CompetitionNumberService competitionNumberService;

	@Autowired
	public CompetitorServiceImpl(ParticipantRepository participantRepository,
															 CompetitorRepository competitorRepository,
															 CompetitionService competitionService,
															 CompetitionNumberService competitionNumberService) {
		this.participantRepository = participantRepository;
		this.competitorRepository = competitorRepository;
		this.competitionService = competitionService;
		this.competitionNumberService = competitionNumberService;
	}

	@Override
	public Optional<Participant> findById(long participantId) {
		return participantRepository.findById(participantId);
	}

	@Override
	public Participant register(CompetitorParticipantHolder participantHolder, long distanceId, long competitionId) {
		log.info("Participant registration started: " + getCompetitorFullName(participantHolder.getCompetitor()));
		CompetitionDistance competitionDistance = competitionService.getCompetitionDistance(distanceId, competitionId)
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", distanceId));
		Competitor competitor = competitorRepository.save(participantHolder.getCompetitor());
		Participant participant = participantHolder.getParticipant();
		participant.setCompetitor(competitor);
		participant.setCompetitionDistance(competitionDistance);
		Participant result =  participantRepository.save(participant);
		log.info("Participant successfully registered: " + getCompetitorFullName(result.getCompetitor()));

		return result;
	}

	@Override
	public Participant updateParticipantPayment(Long participantId) {
		Participant participant = participantRepository.findById(participantId)
			.orElseThrow(() -> new ResourceNotFoundException(Participant.class.getName(), "participantId", participantId));
		log.info("Updating participant payment: " + getCompetitorFullName(participant.getCompetitor()));
		participant.setPaymentFulfilled(!participant.isPaymentFulfilled());
		if (Objects.isNull(participant.getCompetitorNumber())) {
			int competitorNumber = competitionNumberService.generateCompetitorNumber(participant.getCompetitionDistance(), participant);
			participant.setCompetitorNumber(competitorNumber);
		}
		return participantRepository.save(participant);
	}

	@Override
	public Participant updateParticipant(Participant participant) {
		return participantRepository.save(participant);
	}

	@Override
	public Participant save(Participant participant) {
		return null;
	}

	@Override
	public void deleteParticipant(long participantId) {
		Participant participant = participantRepository.findById(participantId)
			.orElseThrow(() -> new ResourceNotFoundException(Participant.class.getName(), "participantId", participantId));
		participantRepository.delete(participant);
	}
}
