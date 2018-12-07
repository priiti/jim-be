package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumberGeneratorServiceImpl implements NumberGeneratorService {
	private final ParticipantRepository participantRepository;

	@Override
	public Integer generateCompetitorNumber(CompetitionDistance competitionDistance, int startingNumber) {
		return null;
	}
}
