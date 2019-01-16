package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class CompetitorDtoFactory {
	private final CompetitorRegistrationConverter registrationConverter;
	private final CompetitorConverter competitorConverter;
	private final ParticipantConverter participantConverter;

	@Autowired
	public CompetitorDtoFactory(CompetitorRegistrationConverter registrationConverter,
															CompetitorConverter competitorConverter,
															ParticipantConverter participantConverter) {
		this.registrationConverter = registrationConverter;
		this.competitorConverter = competitorConverter;
		this.participantConverter = participantConverter;
	}

	public Competitor getCompetitor(ParticipantRegistrationDto registrationDto) {
		return registrationConverter.convertDtoToCompetitor(registrationDto);
	}

	public Participant getParticipant(ParticipantRegistrationDto registrationDto) {
		return registrationConverter.convertDtoToParticipant(registrationDto);
	}

	public Competitor getCompetitor(ParticipantDto participantDto) {
		return competitorConverter.convertDto(participantDto);
	}

	public Participant getParticipant(ParticipantDto participantDto) {
		return participantConverter.convertDto(participantDto);
	}
}
