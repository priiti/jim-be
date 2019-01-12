package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.RegistrationHolder;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competitor.CompetitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ee.sport.jim.webapp.rest.exception.ErrorConstants.RESOURCE_NOT_FOUND;

@Slf4j
@Service
public class CompetitorRestInteractor implements CompetitorRestService {
	private final CompetitorService competitorService;
	private final CompetitorDtoFactory competitorDtoFactory;

	public CompetitorRestInteractor(CompetitorService competitorService, CompetitorDtoFactory competitorDtoFactory) {
		this.competitorService = competitorService;
		this.competitorDtoFactory = competitorDtoFactory;
	}

	@Override
	public void register(ParticipantRegistrationDto participantRegistrationDto) {
		log.info("Registering participant");
		competitorService.register(createRegistrationHolder(participantRegistrationDto),
			participantRegistrationDto.getCompetitionDistanceId());
	}

	@Override
	public ResponseEntity<?> updateParticipantPaymentInfo(Long participantId) {
		Optional<Participant> optionalParticipant = competitorService.updateParticipantPaymentInfo(participantId);
		if (!optionalParticipant.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Participant not found with ID: " + participantId);
		}
		return ResponseEntity.ok().build();
	}

	private RegistrationHolder createRegistrationHolder(ParticipantRegistrationDto registrationDto) {
		RegistrationHolder registrationHolder = new RegistrationHolder();
		registrationHolder.setCompetitor(competitorDtoFactory.getCompetitor(registrationDto));
		registrationHolder.setParticipant(competitorDtoFactory.getParticipant(registrationDto));
		return registrationHolder;
	}
}
