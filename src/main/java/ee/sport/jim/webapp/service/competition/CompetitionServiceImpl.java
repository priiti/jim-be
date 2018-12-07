package ee.sport.jim.webapp.service.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.repository.CompetitionDistanceRepository;
import ee.sport.jim.webapp.repository.CompetitionRepository;
import ee.sport.jim.webapp.repository.ParticipantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
	private final CompetitionRepository competitionRepository;
	private final CompetitionDistanceRepository distanceRepository;
	private final ParticipantRepository participantRepository;

	@Autowired
	public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionDistanceRepository distanceRepository,
																ParticipantRepository participantRepository) {
		this.competitionRepository = competitionRepository;
		this.distanceRepository = distanceRepository;
		this.participantRepository = participantRepository;
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
