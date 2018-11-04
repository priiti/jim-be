package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.repository.CompetitionDistanceRepository;
import ee.sport.jim.webapp.repository.CompetitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
	private final CompetitionRepository competitionRepository;
	private final CompetitionDistanceRepository competitionDistanceRepository;

	public CompetitionServiceImpl(CompetitionRepository competitionRepository,
																CompetitionDistanceRepository competitionDistanceRepository) {
		this.competitionRepository = competitionRepository;
		this.competitionDistanceRepository = competitionDistanceRepository;
	}

	@Override
	public Optional<Competition> getCompetitionForRegistration(long competitionId) {
		return competitionRepository.findById(competitionId);
	}

	@Override
	public Optional<CompetitionDistance> getCompetitionDistance(long competitionDistanceId) {
		return competitionDistanceRepository.findById(competitionDistanceId);
	}
}
