package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CompetitorDtoFactory {
	private final CompetitorRegistrationConverter registrationConverter;
	private final ParticipantConverter participantConverter;

	@Autowired
	public CompetitorDtoFactory(CompetitorRegistrationConverter registrationConverter,
															ParticipantConverter participantConverter) {
		this.registrationConverter = registrationConverter;
		this.participantConverter = participantConverter;
	}

	public Competitor getCompetitor(ParticipantRegistrationDto registrationDto) {
		return registrationConverter.convertDtoToCompetitor(registrationDto);
	}

	public Participant getParticipant(ParticipantRegistrationDto registrationDto) {
		return registrationConverter.convertDtoToParticipant(registrationDto);
	}

	public ParticipantDto convertParticipant(Participant participant) {
		return participantConverter.convertEntity(participant);
	}

	public List<ParticipantDto> convertParticipants(List<Participant> participants) {
		return participantConverter.convertEntity(participants);
	}
}
