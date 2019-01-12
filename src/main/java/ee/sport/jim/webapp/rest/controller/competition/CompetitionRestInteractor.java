package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.ResultSetEnum;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantsInfoDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
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
	public ParticipantsInfoDto getPaidCompParticipants(long competitionId, long distanceId, Integer pageNumber, Integer resultLimit) {
		Optional<CompetitionDistance> optionalDistance = competitionService.getCompetitionDistance(distanceId);
		if (!optionalDistance.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
		validateCompetition(optionalDistance.get(), competitionId, distanceId);

		int page = pageNumber != null ? pageNumber : ResultSetEnum.DEFAULT_PAGE_NUMBER.getValue();
		int limit = resultLimit != null ? resultLimit : ResultSetEnum.DEFAULT_PAGE_RESULT_LIMIT.getValue();
		Page<Participant> participants = competitionService.getPaidCompetitionParticipants(distanceId, PageRequest.of(page, limit));
		ParticipantsInfoDto participantsInfoDto = competitionDtoFactory.getPublicCompParticipantsInfo(participants.getContent());
		participantsInfoDto.setDistanceParticipantCount(participants.getTotalElements());
		return participantsInfoDto;
	}

	@Override
	public ParticipantsInfoDto getAllCompParticipants(long competitionId, long distanceId, Integer pageNumber, Integer resultLimit) {
		Optional<CompetitionDistance> optionalDistance = competitionService.getCompetitionDistance(distanceId);
		if (!optionalDistance.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
		validateCompetition(optionalDistance.get(), competitionId, distanceId);
		int page = pageNumber != null ? pageNumber : ResultSetEnum.DEFAULT_PAGE_NUMBER.getValue();
		int limit = resultLimit != null ? resultLimit : ResultSetEnum.DEFAULT_PAGE_RESULT_LIMIT.getValue();
		Page<Participant> participants = competitionService.getAllCompetitionParticipants(distanceId, PageRequest.of(page, limit));
		ParticipantsInfoDto participantsInfoDto = competitionDtoFactory.getPrivateCompParticipantsInfo(participants.getContent());
		participantsInfoDto.setDistanceParticipantCount(participants.getTotalElements());
		return participantsInfoDto;
	}

	@Override
	public CompDistanceInfoDto getCompetitionDistanceInfo(long competitionId) {
		Optional<Competition> optionalCompetition = competitionService.findById(competitionId);
		if (!optionalCompetition.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
		return competitionDtoFactory.getCompetitionDistancesInfo(optionalCompetition.get());
	}

	private void validateCompetition(CompetitionDistance distance, long competitionId, long distanceId) {
		if (!distance.getCompetition().getId().equals(competitionId) || !distance.getId().equals(distanceId)) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
	}
}
