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
	public Participant register(CompetitorParticipantHolder registrationHolder) {
		log.info("Participant registration started: " + getCompetitorFullName(registrationHolder.getCompetitor()));
		CompetitionDistance competitionDistance = competitionService.getCompetitionDistance(
			registrationHolder.getCompetitionDistanceId(), registrationHolder.getCompetitionId())
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId",
				registrationHolder.getCompetitionDistanceId()));
		Competitor competitor = competitorRepository.save(registrationHolder.getCompetitor());
		Participant participant = registrationHolder.getParticipant();
		participant.setCompetitor(competitor);
		participant.setCompetitionDistance(competitionDistance);
		Participant result =  participantRepository.save(participant);
		log.info("Participant successfully registered: " + getCompetitorFullName(result.getCompetitor()));
		return result;
	}

	@Override
	public Participant updateParticipantPayment(long participantId) {
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
	public Participant updateParticipant(Participant changeParticipant) {
		Participant existingParticipant = participantRepository.findById(changeParticipant.getId())
			.orElseThrow(() -> new ResourceNotFoundException(Participant.class.getName(), "participantId", changeParticipant.getId()));
		prepareForSave(changeParticipant, existingParticipant);
		return participantRepository.save(existingParticipant);
	}

	private void prepareForSave(Participant changeParticipant, Participant existingParticipant) {
		existingParticipant.getCompetitor().setFirstName(changeParticipant.getCompetitor().getFirstName());
		existingParticipant.getCompetitor().setLastName(changeParticipant.getCompetitor().getLastName());
		existingParticipant.getCompetitor().setDateOfBirth(changeParticipant.getCompetitor().getDateOfBirth());
		existingParticipant.getCompetitor().setGender(changeParticipant.getCompetitor().getGender());
		existingParticipant.getCompetitor().setPublishData(changeParticipant.getCompetitor().isPublishData());
		existingParticipant.getCompetitor().setNewsletterSubscription(changeParticipant.getCompetitor().isNewsletterSubscription());
		existingParticipant.getCompetitor().setSportsClub(changeParticipant.getCompetitor().getSportsClub());
		existingParticipant.getCompetitor().setPhoneNumber(changeParticipant.getCompetitor().getPhoneNumber());
		existingParticipant.getCompetitor().setEmail(changeParticipant.getCompetitor().getEmail());
		existingParticipant.setEnvelopePrinted(changeParticipant.isEnvelopePrinted());
		existingParticipant.setPaymentFulfilled(changeParticipant.isPaymentFulfilled());
		existingParticipant.setChampionshipParticipation(changeParticipant.isChampionshipParticipation());
		existingParticipant.setNumberPrinted(changeParticipant.isNumberPrinted());
		existingParticipant.setCompetitorNumber(changeParticipant.getCompetitorNumber());
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

	@Override
	public boolean ifParticipantExistsBy(long id) {
		return participantRepository.existsById(id);
	}
}
