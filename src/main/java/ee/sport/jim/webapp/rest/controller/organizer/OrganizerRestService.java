package ee.sport.jim.webapp.rest.controller.organizer;

import ee.sport.jim.webapp.rest.dto.organizer.OrganizerInfoDto;

import java.util.List;

public interface OrganizerRestService {

	List<OrganizerInfoDto> getOrganizersByCompetitionId(long competitionId);

}
