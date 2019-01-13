package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.RegistrationHolder;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.rest.util.URIBuilder;
import ee.sport.jim.webapp.service.competitor.CompetitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

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
			participantRegistrationDto.getCompetitionDistanceId(), participantRegistrationDto.getCompetitionId());
	}

	@Override
	public ResponseEntity<?> updateParticipantPaymentInfo(Long participantId) {
		Participant participant = competitorService.updateParticipantPaymentInfo(participantId)
			.orElseThrow(() -> new ResourceNotFoundException("Participant", "participantId", participantId));
		URI location = URIBuilder.create("/api/v1/competitor/{participantId}",
			participant.getCompetitor().getFirstName() + " " + participant.getCompetitor().getLastName());
		return ResponseEntity.created(location).body(new ApiResponse(true, "Participant updated."));
	}

	private RegistrationHolder createRegistrationHolder(ParticipantRegistrationDto registrationDto) {
		RegistrationHolder registrationHolder = new RegistrationHolder();
		registrationHolder.setCompetitor(competitorDtoFactory.getCompetitor(registrationDto));
		registrationHolder.setParticipant(competitorDtoFactory.getParticipant(registrationDto));
		return registrationHolder;
	}
}
