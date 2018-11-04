package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.CompParticipantRegistrationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompetitorDtoFactory {
	private static final CompetitorConverter competitorConverter = new CompetitorConverter();
	private static final ParticipantConverter participantConverter = new ParticipantConverter();

	public static Competitor getCompetitor(CompParticipantRegistrationDto registrationDto) {
		return competitorConverter.convertDto(registrationDto);
	}

	public static Participant getParticipant(CompParticipantRegistrationDto registrationDto) {
		return participantConverter.convertDto(registrationDto);
	}
}
