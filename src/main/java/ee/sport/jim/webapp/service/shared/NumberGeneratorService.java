package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import org.springframework.stereotype.Service;

@Service
public interface NumberGeneratorService {

	Integer generateCompetitorNumber(CompetitionDistance competitionDistance);

}
