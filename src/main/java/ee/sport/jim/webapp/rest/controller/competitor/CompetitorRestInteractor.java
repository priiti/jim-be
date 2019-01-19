package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.CompetitorParticipantHolder;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.rest.util.URIBuilder;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import ee.sport.jim.webapp.service.competitor.CompetitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static ee.sport.jim.webapp.rest.util.CompetitorUtil.getFullName;

@Slf4j
@Service
public class CompetitorRestInteractor implements CompetitorRestService {
	private final CompetitorService competitorService;
	private final CompetitorDtoFactory competitorDtoFactory;
	private final CompetitionService competitionService;

	@Autowired
	public CompetitorRestInteractor(CompetitorService competitorService,
																	CompetitorDtoFactory competitorDtoFactory,
																	CompetitionService competitionService) {
		this.competitorService = competitorService;
		this.competitorDtoFactory = competitorDtoFactory;
		this.competitionService = competitionService;
	}

	@Override
	public ResponseEntity<?> register(ParticipantRegistrationDto participantRegistrationDto) {
		CompetitionDistance distance = competitionService.getCompetitionDistance(participantRegistrationDto.getCompetitionDistanceId())
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", participantRegistrationDto.getCompetitionDistanceId()));
		Participant participant = competitorService.register(createRegistrationHolder(participantRegistrationDto),
			distance.getId(), distance.getCompetition().getId());
		URI location = URIBuilder.create("/api/v1/competitor/register", getFullName(participant));
		return ResponseEntity
			.created(location)
			.body(new ApiResponse(true, "Participant registered: " + getFullName(participant)));
	}

	@Override
	public ResponseEntity<?> updateParticipantPayment(long participantId) {
		Participant participant = competitorService.updateParticipantPayment(participantId);
		log.info("Participant update completed");
		URI location = URIBuilder.create("/api/v1/competitor/{participantId}", getFullName(participant));
		return ResponseEntity
			.created(location)
			.body(new ApiResponse(true, "Participant updated: " + getFullName(participant)));
	}

	@Override
	public ResponseEntity<?> removeParticipant(long participantId) {
		competitorService.deleteParticipant(participantId);
		return ResponseEntity.ok(new ApiResponse(true, "Participant deleted"));
	}

	@Override
	public ResponseEntity<?> updateCompetitorParticipant(ParticipantDto participantDto) {
		CompetitorParticipantHolder holder = new CompetitorParticipantHolder();
		holder.setCompetitor(competitorDtoFactory.getCompetitor(participantDto));
		holder.setParticipant(competitorDtoFactory.getParticipant(participantDto));
		CompetitionDistance distance = competitionService.getCompetitionDistance(participantDto.getDistanceId())
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", participantDto.getDistance()));
		holder.getParticipant().setCompetitionDistance(distance);
		Participant participant = competitorService.updateCompetitorParticipant(holder);
		log.info("Participant update completed");
		URI location = URIBuilder.create("/api/v1/competitor/private/update", getFullName(participant));
		return ResponseEntity
			.created(location)
			.body(new ApiResponse(true, "Participant updated: " + getFullName(participant)));
	}

	private CompetitorParticipantHolder createRegistrationHolder(ParticipantRegistrationDto registrationDto) {
		CompetitorParticipantHolder competitorParticipantHolder = new CompetitorParticipantHolder();
		competitorParticipantHolder.setCompetitor(competitorDtoFactory.getCompetitor(registrationDto));
		competitorParticipantHolder.setParticipant(competitorDtoFactory.getParticipant(registrationDto));
		return competitorParticipantHolder;
	}
}
