package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.ParticipantItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	Optional<Competition> findById(long competitionId);

	List<Competition> findByName(String name);

	@Query(value = "SELECT * FROM v_comp_participant_list WHERE competition_id = :competitionId", nativeQuery = true)
	List<ParticipantItem> findCompetitionParticipants(@Param("competitionId") Long competitionId);
}
