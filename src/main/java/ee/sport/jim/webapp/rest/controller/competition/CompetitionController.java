package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.domain.competition.ParticipantItem;
import ee.sport.jim.webapp.repository.CompetitionRepository;
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
	private final CompetitionRepository competitionRepository;

	public CompetitionController(CompetitionRestService competitionRestService, CompetitionRepository competitionRepository) {
		this.competitionRestService = competitionRestService;
		this.competitionRepository = competitionRepository;
	}

	@GetMapping(value = "/{competitionId}/registration", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto competitionRegistration(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionForRegistration(competitionId);
	}

	@GetMapping(value = "/{competitionId}/participants", produces = APPLICATION_JSON_VALUE)
	public List<ParticipantItem> getCompetitionParticipants(@PathVariable @NotNull final Long competitionId) {
		return competitionRepository.findCompetitionParticipants(competitionId);
	}
}
