package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	List<Organizer> findOrganizerByIdIn(List<Long> organizerIds);

}
