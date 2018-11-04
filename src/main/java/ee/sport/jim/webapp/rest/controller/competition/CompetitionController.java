package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/competition")
public class CompetitionController {
	private final CompetitionRestService competitionRestService;

	public CompetitionController(CompetitionRestService competitionRestService) {
		this.competitionRestService = competitionRestService;
	}

	@GetMapping(value = "/{competitionId}/register", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto registerCompetition(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionForRegistration(competitionId);
	}

}
