package ee.sport.jim.webapp.rest.controller.competition;

import ee.sport.jim.webapp.rest.dto.PagedResponse;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static ee.sport.jim.webapp.rest.util.AppConstants.DEFAULT_PAGE_NUMBER;
import static ee.sport.jim.webapp.rest.util.AppConstants.DEFAULT_PAGE_SIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/competition")
public class CompetitionController {
	private final CompetitionRestService competitionRestService;

	@Autowired
	public CompetitionController(CompetitionRestService competitionRestService) {
		this.competitionRestService = competitionRestService;
	}

	@GetMapping(value = "/public/{competitionId}/registration", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto getCompetitionForRegistration(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionForRegistration(competitionId);
	}

	@GetMapping(value = "/public/{competitionId}/participants/{distanceId}", produces = APPLICATION_JSON_VALUE)
	public PagedResponse<ParticipantDto> getPublicCompParticipants(@PathVariable @NotNull final Long competitionId,
																								 @PathVariable @NotNull final Long distanceId,
																								 @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
																								 @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
		return competitionRestService.getPublicParticipants(competitionId, distanceId, page, size);
	}

	@GetMapping(value = "/public/{competitionId}/info", produces = APPLICATION_JSON_VALUE)
	public CompetitionDto getCompetitionDistance(@PathVariable @NotNull final Long competitionId) {
		return competitionRestService.getCompetitionInformation(competitionId);
	}

	@GetMapping(value = "/private/{competitionId}/participants/{distanceId}", produces = APPLICATION_JSON_VALUE)
	public PagedResponse<ParticipantDto> getPrivateCompParticipants(@PathVariable @NotNull final Long competitionId,
																												@PathVariable @NotNull final Long distanceId,
																												@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
																												@RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
		return competitionRestService.getPrivateParticipants(competitionId, distanceId, page, size);
	}
}
