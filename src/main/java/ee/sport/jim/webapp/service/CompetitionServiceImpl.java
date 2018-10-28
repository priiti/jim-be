package ee.sport.jim.webapp.service;

import ee.sport.jim.webapp.domain.Competition;
import ee.sport.jim.webapp.repository.CompetitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
	private final CompetitionRepository competitionRepository;

	public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
		this.competitionRepository = competitionRepository;
	}

	@Override
	public List<Competition> getCompetitions() {
		log.info("GET all competitions");
		return competitionRepository.findAll();
	}

	@Override
	public Optional<Competition> getCompetition(long competitionId) {
		log.info("GET competition by id: " + competitionId);
		return competitionRepository.findById(competitionId);
	}
}
