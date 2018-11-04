package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.CompParticipantRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/competitor")
public class CompetitorController {
	private final CompetitorRestService competitorRestService;

	public CompetitorController(CompetitorRestService competitorRestService) {
		this.competitorRestService = competitorRestService;
	}

	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity register(@RequestBody @NotNull @Valid CompParticipantRegistrationDto participantRegistrationDto) {
		competitorRestService.register(participantRegistrationDto);
		return ResponseEntity.ok().build();
	}
}
