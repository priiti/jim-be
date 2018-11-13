package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompParticipantInfoConverter implements Converter<List<Participant>, CompParticipantInfoDto> {

	@Override
	public CompParticipantInfoDto convert(List<Participant> participants) {
		CompParticipantInfoDto compParticipantInfoDto = new CompParticipantInfoDto();
		List<ParticipantInfoDto> participantsDto = new ArrayList<>();
		for (Participant participant : participants) {
			ParticipantInfoDto infoDto = convertParticipantInfoDto(participant);
			participantsDto.add(infoDto);
		}
		compParticipantInfoDto.setParticipants(participantsDto);
		return compParticipantInfoDto;
	}

	private ParticipantInfoDto convertParticipantInfoDto(Participant participant) {
		ParticipantInfoDto dto = new ParticipantInfoDto();
		dto.setParticipantId(participant.getId());
		boolean publishData = participant.getCompetitor().isPublishData();
		if (publishData) {
			dto.setFirstName(participant.getCompetitor().getFirstName());
			dto.setLastName(participant.getCompetitor().getLastName());
		}
		dto.setCompetitorNumber(participant.getCompetitorNumber());
		return dto;
	}
}
