package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/competition")
public class CompetitionController {
	private final CompetitionRestService competitionRestService;

	@Autowired
	public CompetitionController(CompetitionRestService competitionRestService) {
		this.competitionRestService = competitionRestService;
	}

	@GetMapping(value = "/{competitionId}/registration", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto getCompetitionDataForRegistration(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionForRegistration(competitionId);
	}

	@GetMapping(value = "/{competitionId}/participants/{distanceId}", produces = APPLICATION_JSON_VALUE)
	public CompParticipantInfoDto getCompetitionParticipants(@RequestParam final Integer pageNumber, final Integer limit,
																													 @PathVariable @NotNull final Long competitionId,
																													 @PathVariable @NotNull final Long distanceId) {
		return competitionRestService.getCompetitionParticipants(competitionId, distanceId, pageNumber, limit);
	}

	@GetMapping(value = "/{competitionId}/info", produces = APPLICATION_JSON_VALUE)
	public CompDistanceInfoDto getCompetitionDistance(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionDistanceInfo(competitionId);
	}
}
