package ee.sport.jim.webapp.service.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizerService extends CrudService<Organizer, Long> {

	List<Organizer> getOrganizersByCompetitionId(long competitionId);

}
