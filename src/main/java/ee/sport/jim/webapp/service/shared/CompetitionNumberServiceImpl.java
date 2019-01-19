package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.repository.CompetitionDistanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ee.sport.jim.webapp.service.CompetitionUtils.getCompetitionDistanceName;
import static ee.sport.jim.webapp.service.CompetitionUtils.getCompetitorFullName;

@Slf4j
@Service
public class CompetitionNumberServiceImpl implements CompetitionNumberService {
	private final CompetitionDistanceRepository competitionDistanceRepository;

	@Autowired
	public CompetitionNumberServiceImpl(CompetitionDistanceRepository competitionDistanceRepository) {
		this.competitionDistanceRepository = competitionDistanceRepository;
	}
	
	@Override
	public int generateCompetitorNumber(CompetitionDistance distance, Participant participant) {
		return createCompetitorNumber(distance, participant);
	}

	@Override
	public int getDistanceStartingNumber(CompetitionDistance competitionDistance) {
		return competitionDistance.getStartNumbering();
	}

	private Integer createCompetitorNumber(CompetitionDistance distance, Participant participant) {
		log.info("Competitor number generation for participant: " + getCompetitorFullName(participant.getCompetitor())
		 + ", id:" + participant.getId());
		if (participant.getCompetitorNumber() != null) {
			log.info("Participant already has number");
			return participant.getCompetitorNumber();
		}
		Optional<Integer> currentCompetitorNumber = Optional.ofNullable(distance.getCurrentCompetitorNumber());
		if (!currentCompetitorNumber.isPresent()) {
			Integer competitionDistanceStartingNumber = distance.getStartNumbering();
			int competitorNumber = competitionDistanceStartingNumber < 1 ? 1 : competitionDistanceStartingNumber;
			distance.setCurrentCompetitorNumber(competitorNumber);
			CompetitionDistance result = competitionDistanceRepository.save(distance);
			log.info("Number saved for: " + getCompetitionDistanceName(result) + ", new currentCompetitorNumber is now: " + competitorNumber);
			return competitorNumber;
		} else {
			int competitorNumber = currentCompetitorNumber.get() + 1;
			distance.setCurrentCompetitorNumber(competitorNumber);
			CompetitionDistance result = competitionDistanceRepository.save(distance);
			log.info("Number saved for: " + getCompetitionDistanceName(result) + ", new currentCompetitorNumber is now: " + competitorNumber);
			return competitorNumber;
		}
	}
}
