package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionDistanceRepository extends JpaRepository<CompetitionDistance, Long> {

	boolean existsByCompetitionId(long competitionId);

	Optional<CompetitionDistance> findByIdAndCompetitionId(long distanceId, long competitionId);

}
