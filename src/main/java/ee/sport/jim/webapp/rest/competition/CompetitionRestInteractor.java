package ee.sport.jim.webapp.rest.competition;

import ee.sport.jim.webapp.domain.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDtoFactory;
import ee.sport.jim.webapp.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionRestInteractor implements CompetitionRestService {
	private final CompetitionService competitionService;

	public CompetitionRestInteractor(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	@Override
	public List<CompetitionDto> getCompetitions() {
		List<Competition> competitions = competitionService.getCompetitions();
		return CompetitionDtoFactory.getCompetitionDtos(competitions);
	}

	@Override
	public CompetitionDto getCompetition(long competitionId) {
		Optional<Competition> competitionOptional = competitionService.getCompetition(competitionId);
		if (!competitionOptional.isPresent()) {
			throw new RuntimeException();
		}
		return CompetitionDtoFactory.getCompetitionDto(competitionOptional.get());
	}
}
