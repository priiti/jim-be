package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.competitor.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	Page<Participant> findByCompetitionDistanceIdAndPaymentFulfilled(long competitionDistanceId, boolean paymentFulfilled, Pageable page);

	Page<Participant> findByCompetitionDistanceId(long competitionDistanceId, Pageable page);

}
