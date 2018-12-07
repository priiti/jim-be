package ee.sport.jim.webapp.rest.controller.organizer;

import ee.sport.jim.webapp.rest.dto.organizer.OrganizerInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/organizer")
@RequiredArgsConstructor
public class OrganizerController {
	private final OrganizerRestService organizerRestService;

	@GetMapping(value = "/info/{competitionId}")
	public List<OrganizerInfoDto> getOrganizerInfo(@PathVariable @NotNull final Long competitionId) {
		return organizerRestService.getOrganizersByCompetitionId(competitionId);
	}

}
