package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.RegistrationHolder;
import ee.sport.jim.webapp.repository.CompetitorRepository;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import ee.sport.jim.webapp.service.shared.NumberGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitorServiceImpl implements CompetitorService {
	private final ParticipantRepository participantRepository;
	private final CompetitorRepository competitorRepository;
	private final CompetitionService competitionService;
	private final NumberGeneratorService numberGeneratorService;

	@Override
	public Participant register(RegistrationHolder registrationHolder, long distanceId) {
		String registrantInfo = String.format("%s %s, %s", registrationHolder.getCompetitor().getFirstName(),
			registrationHolder.getCompetitor().getLastName(), registrationHolder.getCompetitor().getEmail());
		log.info("Registering participant: " + registrantInfo);
		Optional<CompetitionDistance> optionalDistance = competitionService.getCompetitionDistance(distanceId);
		if (!optionalDistance.isPresent()) {
			log.error("Registration failed for user: " + registrantInfo);
			throw new ResourceNotFoundException(String.format("Distance with ID {%d} not found.", distanceId));
		}
		Competitor competitor = competitorRepository.save(registrationHolder.getCompetitor());
		Participant participant = registrationHolder.getParticipant();
		participant.setCompetitor(competitor);
		participant.setCompetitionDistance(optionalDistance.get());
		Participant registeredParticipant =  participantRepository.save(participant);
		log.info("Registered participant: " + registrantInfo);
		return registeredParticipant;
	}

	@Override
	public Optional<Participant> updateParticipantPaymentInfo(Long participantId) {
		Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
		if (!optionalParticipant.isPresent()) {
			return Optional.empty();
		}
		Participant participant = optionalParticipant.get();
		participant.setPaymentFulfilled(!participant.isPaymentFulfilled());
		if (Objects.isNull(participant.getCompetitorNumber())) {
			Integer competitorNumber = numberGeneratorService.generateCompetitorNumber(participant.getCompetitionDistance());
			participant.setCompetitorNumber(competitorNumber);
		}
		return Optional.of(participantRepository.save(participant));
	}
}
