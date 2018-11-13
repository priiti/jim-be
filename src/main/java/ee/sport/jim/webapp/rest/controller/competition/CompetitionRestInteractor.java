package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ee.sport.jim.webapp.rest.exception.ErrorConstants.RESOURCE_NOT_FOUND;

@Slf4j
@Service
public class CompetitionRestInteractor implements CompetitionRestService {
	private final CompetitionService competitionService;
	private final CompetitionDtoFactory competitionDtoFactory;

	public CompetitionRestInteractor(CompetitionService competitionService, CompetitionDtoFactory competitionDtoFactory) {
		this.competitionService = competitionService;
		this.competitionDtoFactory = competitionDtoFactory;
	}

	@Override
	public CompetitionDto getCompetitionForRegistration(long competitionId) {
		log.info("Getting competition by id: " + competitionId);
		Optional<Competition> competitionOptional = competitionService.findById(competitionId);
		if (!competitionOptional.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
		return competitionDtoFactory.getCompetitionForRegistrationDto(competitionOptional.get());
	}

	@Override
	public CompParticipantInfoDto getCompetitionParticipants(long competitionId, long distanceId, Integer pageNumber, Integer limit) {
		Optional<CompetitionDistance> optionalDistance = competitionService.getCompetitionDistance(distanceId);
		if (!optionalDistance.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + distanceId);
		}
		validateCompetition(optionalDistance.get(), competitionId, distanceId);
		Page<Participant> participants = competitionService.getCompetitionParticipants(distanceId, PageRequest.of(pageNumber, limit));
		if (participants.getTotalElements() < 1) {
			return null;
		}
		CompParticipantInfoDto participantInfo = competitionDtoFactory.getCompetitionParticipantsInfo(participants.getContent());
		participantInfo.setDistanceParticipantCount(participants.getTotalElements());
		return participantInfo;
	}

	private void validateCompetition(CompetitionDistance distance, long competitionId, long distanceId) {
		if (!distance.getCompetition().getId().equals(competitionId) || !distance.getId().equals(distanceId)) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + distanceId);
		}
	}
}
