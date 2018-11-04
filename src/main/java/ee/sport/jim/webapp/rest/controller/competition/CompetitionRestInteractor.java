package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
			throw new RuntimeException();
		}
		Competition competition = competitionOptional.get();
		return CompetitionDtoFactory.getCompetitionDtoForRegistration(competition);
	}
}
