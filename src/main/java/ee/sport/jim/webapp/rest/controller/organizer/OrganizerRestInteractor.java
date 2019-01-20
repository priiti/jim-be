package ee.sport.jim.webapp.rest.controller.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.rest.dto.converter.organizer.OrganizerConverter;
import ee.sport.jim.webapp.rest.dto.organizer.OrganizerDto;
import ee.sport.jim.webapp.service.organizer.OrganizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrganizerRestInteractor implements OrganizerRestService {
	private final OrganizerService organizerService;
	private final OrganizerConverter organizerConverter;

	@Autowired
	public OrganizerRestInteractor(OrganizerService organizerService,
																 OrganizerConverter organizerConverter) {
		this.organizerService = organizerService;
		this.organizerConverter = organizerConverter;
	}

	@Override
	public List<OrganizerDto> getOrganizersByCompetitionId(long competitionId) {
		log.info("Searcing organizer by competition id: " + competitionId);
		List<Organizer> organizers = organizerService.getOrganizersByCompetitionId(competitionId);
		return organizerConverter.convertEntity(organizers);
	}
}
