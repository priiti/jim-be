package ee.sport.jim.webapp.rest.controller.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.rest.dto.converter.organizer.OrganizerInfoConverter;
import ee.sport.jim.webapp.rest.dto.organizer.OrganizerInfoDto;
import ee.sport.jim.webapp.service.organizer.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizerRestInteractor implements OrganizerRestService {
	private final OrganizerService organizerService;
	private final OrganizerInfoConverter organizerInfoConverter;

	@Autowired
	public OrganizerRestInteractor(OrganizerService organizerService,
																 OrganizerInfoConverter organizerInfoConverter) {
		this.organizerService = organizerService;
		this.organizerInfoConverter = organizerInfoConverter;
	}

	@Override
	public List<OrganizerInfoDto> getOrganizersByCompetitionId(long competitionId) {
		List<Organizer> organizers = organizerService.getOrganizersByCompetitionId(competitionId);
		return CollectionUtils.isEmpty(organizers) ? new ArrayList<>() :
			organizerInfoConverter.convert(organizers);
	}
}
