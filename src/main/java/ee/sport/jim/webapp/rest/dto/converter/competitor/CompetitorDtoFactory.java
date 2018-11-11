package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public final class CompetitorDtoFactory {
	private final CompetitorConverter competitorConverter;
	private final ParticipantConverter participantConverter;

	public CompetitorDtoFactory(CompetitorConverter competitorConverter, ParticipantConverter participantConverter) {
		this.competitorConverter = competitorConverter;
		this.participantConverter = participantConverter;
	}

	public Competitor getCompetitor(ParticipantRegistrationDto registrationDto) {
		return competitorConverter.convertDto(registrationDto);
	}

	public Participant getParticipant(ParticipantRegistrationDto registrationDto) {
		return participantConverter.convertDto(registrationDto);
	}
}
