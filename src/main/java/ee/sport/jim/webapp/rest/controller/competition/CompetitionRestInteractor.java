package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.PagedResponse;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompetitionRestInteractor implements CompetitionRestService {
	private final CompetitionService competitionService;
	private final CompetitionDtoFactory competitionDtoFactory;

	@Autowired
	public CompetitionRestInteractor(CompetitionService competitionService, CompetitionDtoFactory competitionDtoFactory) {
		this.competitionService = competitionService;
		this.competitionDtoFactory = competitionDtoFactory;
	}

	@Override
	public CompetitionDto getCompetitionForRegistration(long competitionId) {
		log.info("Getting competition by id: " + competitionId);
		Competition competition = competitionService.findById(competitionId)
			.orElseThrow(() -> new ResourceNotFoundException(Competition.class.getName(), "competitionId", competitionId));

		return competitionDtoFactory.getCompetitionForRegistrationDto(competition);
	}

	@Override
	public PagedResponse<ParticipantDto> getPublicParticipants(long competitionId, long distanceId, int page, int size) {
		CompetitionDistance competitionDistance = competitionService.getCompetitionDistance(distanceId, competitionId)
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", distanceId));
		Pageable pageable = PageRequest.of(page, size);
		Page<Participant> participants = competitionService.getPaidCompetitionParticipants(competitionDistance.getId(), pageable);
		List<ParticipantDto> participantDtos = competitionDtoFactory.getParticipantInformation(participants.getContent(), false);

		return new PagedResponse<>(participantDtos, participants.getNumber(), participants.getSize(), participants.getTotalElements(),
			participants.getTotalPages(), participants.isLast()
		);
	}

	@Override
	public PagedResponse<ParticipantDto> getPrivateParticipants(long competitionId, long distanceId, int page, int size) {
		CompetitionDistance competitionDistance = competitionService.getCompetitionDistance(distanceId, competitionId)
			.orElseThrow(() -> new ResourceNotFoundException(CompetitionDistance.class.getName(), "distanceId", distanceId));
		Pageable pageable = PageRequest.of(page, size);
		Page<Participant> participants = competitionService.getAllCompetitionParticipants(competitionDistance.getId(), pageable);
		List<ParticipantDto> participantDtos = competitionDtoFactory.getParticipantInformation(participants.getContent(), true);

		return new PagedResponse<>(participantDtos, participants.getNumber(), participants.getSize(), participants.getTotalElements(),
			participants.getTotalPages(), participants.isLast()
		);
	}

	@Override
	public CompDistanceInfoDto getCompetitionDistanceInfo(long competitionId) {
		Competition competition = competitionService.findById(competitionId)
			.orElseThrow(() -> new ResourceNotFoundException(Competition.class.getName(), "competitionId", competitionId));

		return competitionDtoFactory.getCompetitionDistancesInfo(competition);
	}
}
