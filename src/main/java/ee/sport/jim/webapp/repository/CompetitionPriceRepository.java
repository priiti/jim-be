package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competition.CompetitionPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionPriceRepository extends JpaRepository<CompetitionPrice, Long> {
}
