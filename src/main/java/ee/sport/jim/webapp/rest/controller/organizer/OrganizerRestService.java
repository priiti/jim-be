package ee.sport.jim.webapp.rest.controller.organizer;

import ee.sport.jim.webapp.rest.dto.organizer.OrganizerDto;

import java.util.List;

public interface OrganizerRestService {

	List<OrganizerDto> getOrganizersByCompetitionId(long competitionId);

}
