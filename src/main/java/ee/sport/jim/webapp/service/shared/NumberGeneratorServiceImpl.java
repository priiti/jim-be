package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.repository.CompetitionDistanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NumberGeneratorServiceImpl implements NumberGeneratorService {
	private final CompetitionDistanceRepository distanceRepository;

	@Autowired
	public NumberGeneratorServiceImpl(CompetitionDistanceRepository distanceRepository) {
		this.distanceRepository = distanceRepository;
	}

	@Override
	public Integer generateCompetitorNumber(CompetitionDistance competitionDistance) {
		if (competitionDistance.getCurrentCompetitorNumber() == 0) {
			Integer competitorNumber = competitionDistance.getStartNumbering();
			competitionDistance.setCurrentCompetitorNumber(competitionDistance.getCurrentCompetitorNumber() + 1);
			distanceRepository.save(competitionDistance);
			return competitorNumber;
		} else {
			Integer competitorNumber = competitionDistance.getCurrentCompetitorNumber() + 1;
			competitionDistance.setCurrentCompetitorNumber(competitorNumber);
			distanceRepository.save(competitionDistance);
			return competitorNumber;
		}
	}
}
