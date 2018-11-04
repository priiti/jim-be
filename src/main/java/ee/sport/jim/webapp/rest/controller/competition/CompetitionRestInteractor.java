package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.rest.exception.ResourceNotFoundException;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ee.sport.jim.webapp.rest.exception.ErrorConstants.RESOURCE_NOT_FOUND;

@Slf4j
@Service
public class CompetitionRestInteractor implements CompetitionRestService {
	private final CompetitionService competitionService;

	public CompetitionRestInteractor(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Override
	public CompetitionDto getCompetitionForRegistration(long competitionId) {
		Optional<Competition> competitionOptional = competitionService.getCompetitionForRegistration(competitionId);
		if (!competitionOptional.isPresent()) {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + "Competition with ID: " + competitionId);
		}
		Competition competition = competitionOptional.get();
		return CompetitionDtoFactory.getCompetitionDtoForRegistration(competition);
	}
}
