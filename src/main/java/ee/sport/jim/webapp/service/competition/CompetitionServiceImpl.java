package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.repository.CompetitionDistanceRepository;
import ee.sport.jim.webapp.repository.CompetitionRepository;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import ee.sport.jim.webapp.service.shared.NumberGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
	private final CompetitionRepository competitionRepository;
	private final CompetitionDistanceRepository distanceRepository;
	private final ParticipantRepository participantRepository;
	private final NumberGeneratorService numberGeneratorService;

	@Autowired
	public CompetitionServiceImpl(CompetitionRepository competitionRepository,
																CompetitionDistanceRepository distanceRepository,
																ParticipantRepository participantRepository,
																NumberGeneratorService numberGeneratorService) {
		this.competitionRepository = competitionRepository;
		this.distanceRepository = distanceRepository;
		this.participantRepository = participantRepository;
		this.numberGeneratorService = numberGeneratorService;
	}

	@Override
	public Optional<CompetitionDistance> getCompetitionDistance(long competitionDistanceId) {
		return distanceRepository.findById(competitionDistanceId);
	}

	@Override
	public Page<Participant> getPaidCompetitionParticipants(long distanceId, Pageable page) {
		return participantRepository.findByCompetitionDistanceIdAndPaymentFulfilled(distanceId, true, page);
	}

	@Override
	public Page<Participant> getAllCompetitionParticipants(long distanceId, Pageable page) {
		return participantRepository.findAll(page);
	}

	@Override
	public Optional<Participant> updateParticipantPaymentInfo(Long participantId) {
		Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
		if (!optionalParticipant.isPresent()) {
			return Optional.empty();
		}
		Participant participant = optionalParticipant.get();
		participant.setPaymentFulfilled(!participant.isPaymentFulfilled());
		if (Objects.isNull(participant.getCompetitorNumber())) {
			Integer competitorNumber = numberGeneratorService.generateCompetitorNumber(participant.getCompetitionDistance());
		}
		// TODO -> generate competitor number
		return Optional.of(participantRepository.save(participant));
	}

	@Override
	public boolean existsById(long competitionId) {
		return competitionRepository.existsById(competitionId);
	}

	@Override
	public List<Competition> findAll() {
		return competitionRepository.findAll();
	}

	@Override
	public Optional<Competition> findById(Long competitionId) {
		return competitionRepository.findById(competitionId);
	}

	@Override
	public Competition save(Competition competition) {
		return competitionRepository.save(competition);
	}

	@Override
	public void delete(Competition competition) {
		competitionRepository.delete(competition);
	}

	@Override
	public void deleteBy(Long competitionId) {
		competitionRepository.deleteById(competitionId);
	}
}
