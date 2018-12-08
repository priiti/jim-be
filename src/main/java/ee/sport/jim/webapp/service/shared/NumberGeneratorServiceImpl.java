package ee.sport.jim.webapp.service.shared;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NumberGeneratorServiceImpl implements NumberGeneratorService {
	private final ParticipantRepository participantRepository;

	@Autowired
	public NumberGeneratorServiceImpl(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@Override
	public Integer generateCompetitorNumber(CompetitionDistance competitionDistance) {
		log.info("Generating competitor number for distance: " + competitionDistance.getId());
		List<Participant> participants = participantRepository.findByCompetitionDistanceId(competitionDistance.getId()).stream()
			.filter(participant -> !Objects.isNull(participant.getCompetitorNumber()))
			.collect(Collectors.toList());
		Optional<Integer> optionalNumber = Optional.empty();
		if (!CollectionUtils.isEmpty(participants)) {
			optionalNumber = participants.stream()
				.map(Participant::getCompetitorNumber)
				.max(Integer::compareTo);
		}
		return optionalNumber
			.orElse(competitionDistance.getStartNumbering());
	}
}
