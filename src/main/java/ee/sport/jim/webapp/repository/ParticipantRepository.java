package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competitor.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
