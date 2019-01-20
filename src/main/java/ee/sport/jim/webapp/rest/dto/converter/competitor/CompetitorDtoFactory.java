package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.CompetitorDto;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CompetitorDtoFactory {
	private final ParticipantConverter participantConverter;
	private final CompetitorConverter competitorConverter;

	@Autowired
	public CompetitorDtoFactory(ParticipantConverter participantConverter,
															CompetitorConverter competitorConverter) {
		this.participantConverter = participantConverter;
		this.competitorConverter = competitorConverter;
	}

	public Competitor convertCompetitor(CompetitorDto competitorDto) {
		return competitorConverter.convertDto(competitorDto);
	}

	public Participant convertParticipant(ParticipantDto participantDto) {
		return participantConverter.convertDto(participantDto);
	}

	public List<ParticipantDto> convertParticipants(List<Participant> participants) {
		return participantConverter.convertEntity(participants);
	}
}
