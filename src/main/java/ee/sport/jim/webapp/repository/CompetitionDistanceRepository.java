package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionDistanceRepository extends JpaRepository<CompetitionDistance, Long> {
}
