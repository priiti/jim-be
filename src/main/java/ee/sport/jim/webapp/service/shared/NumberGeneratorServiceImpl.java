package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumberGeneratorServiceImpl implements NumberGeneratorService {
	private final ParticipantRepository participantRepository;

	@Override
	public Integer generateCompetitorNumber(CompetitionDistance competitionDistance) {


		List<Participant> participants = participantRepository.findByCompetitionDistanceId(competitionDistance.getId());
		if (CollectionUtils.isEmpty(participants)) {
			return competitionDistance.getStartNumbering();
		} else {
			Integer currentLargestCompetitorNumber = participants.stream()
				.filter(participant -> !Objects.isNull(participant.getCompetitorNumber()))
				.map(Participant::getCompetitorNumber)
				.sorted((first, second) -> {

				});
		}
	}
}
