package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.CompetitorParticipantHolder;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.competitor.CompetitorDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.rest.dto.converter.competitor.ParticipantConverter;
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
	private final ParticipantConverter participantConverter;

	@Autowired
	public CompetitorRestInteractor(CompetitorService competitorService,
																	CompetitorDtoFactory competitorDtoFactory,
																	CompetitionService competitionService,
																	ParticipantConverter participantConverter) {
		this.competitorService = competitorService;
		this.competitorDtoFactory = competitorDtoFactory;
		this.competitionService = competitionService;
		this.participantConverter = participantConverter;
	}

	@Override
	public ResponseEntity<?> register(ParticipantRegistrationDto participantRegistrationDto) {
		CompetitionDistance distance = competitionService.getCompetitionDistance(participantRegistrationDto.getCompetitionDistanceId())
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", participantRegistrationDto.getCompetitionDistanceId()));
		CompetitorParticipantHolder registrationHolder = createRegistrationHolder(participantRegistrationDto);
		registrationHolder.setCompetitionDistanceId(distance.getId());
		registrationHolder.setCompetitionId(distance.getCompetition().getId());
		Participant participant = competitorService.register(registrationHolder);
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
	public ResponseEntity<?> updateParticipant(ParticipantDto participantDto) {
		if (participantDto.getId() == null || !competitorService.ifParticipantExistsBy(participantDto.getId())) {
			throw new ResourceNotFoundException(Participant.class.getName(), "participantId", participantDto.getId());
		}
		Participant participantChange = participantConverter.convertDto(participantDto);
		Participant result = competitorService.updateParticipant(participantChange);
		log.info("Participant update completed");
		URI location = URIBuilder.create("/api/v1/competitor/private/update", getFullName(result));
		return ResponseEntity
			.created(location)
			.body(new ApiResponse(true, "Participant updated: " + getFullName(result)));
	}

	private CompetitorParticipantHolder createRegistrationHolder(ParticipantRegistrationDto dto) {
		CompetitorParticipantHolder competitorParticipantHolder = new CompetitorParticipantHolder();
		CompetitorDto competitorDto = CompetitorDto.builder()
			.firstName(dto.getFirstName())
			.lastName(dto.getLastName())
			.email(dto.getEmail())
			.gender(dto.getGender())
			.phoneNumber(dto.getPhoneNumber())
			.dateOfBirth(dto.getDateOfBirth())
			.sportsClub(dto.getSportsClub())
			.publishData(dto.isPublishData())
			.newsLetterSubscription(dto.isNewsletterSubscription())
			.build();
		ParticipantDto participantDto = ParticipantDto.builder()
			.participationCount(dto.getParticipationCount())
			.championshipParticipation(dto.isChampionshipParticipation())
			.build();
		competitorParticipantHolder.setCompetitor(competitorDtoFactory.convertCompetitor(competitorDto));
		competitorParticipantHolder.setParticipant(competitorDtoFactory.convertParticipant(participantDto));
		return competitorParticipantHolder;
	}
}
