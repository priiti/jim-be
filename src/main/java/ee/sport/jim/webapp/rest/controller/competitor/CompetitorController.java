package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	public CompetitorController(CompetitorRestService competitorRestService) {
		this.competitorRestService = competitorRestService;
	}

	@PostMapping(value = "/public/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody @NotNull @Valid ParticipantRegistrationDto participantRegistrationDto) {
		return competitorRestService.register(participantRegistrationDto);
	}

	@PostMapping(value = "/private/payment/{participantId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateParticipantPaymentInfo(@PathVariable @NotNull final Long participantId) {
		return competitorRestService.updateParticipantPayment(participantId);
	}

	@PostMapping(value = "/private/update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCompetitor(@RequestBody @NotNull @Valid ParticipantDto participantDto) {
		return competitorRestService.updateParticipant(participantDto);
	}

	@DeleteMapping(value = "/private/remove/{participantId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeParticipant(@NotNull @PathVariable final Long participantId) {
		return competitorRestService.removeParticipant(participantId);
	}
}
