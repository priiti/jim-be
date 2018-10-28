package ee.sport.jim.webapp.rest.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/competition")
public class CompetitionController {
	private final CompetitionRestService competitionRestService;

	public CompetitionController(CompetitionRestService competitionRestService) {
		this.competitionRestService = competitionRestService;
	}

	@GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
	public List<CompetitionDto> getCompetitions() {
		return competitionRestService.getCompetitions();
	}

	@GetMapping(value = "/{competitionId}", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto getCompetition(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetition(competitionId);
	}
}
