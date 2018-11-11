package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	Optional<Competition> findById(long competitionId);

	List<Competition> findByName(String name);

}
