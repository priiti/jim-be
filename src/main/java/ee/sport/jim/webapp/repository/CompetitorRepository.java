package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
}
