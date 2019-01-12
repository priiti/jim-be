package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantsInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParticipantPublicInfoConverter implements Converter<List<Participant>, ParticipantsInfoDto> {

	private Function<Participant, ParticipantDto> convertParticipant = participant -> {
		ParticipantDto dto = new ParticipantDto();
		dto.setParticipantId(participant.getId());
		dto.setCompetitorNumber(participant.getCompetitorNumber());
		if (participant.getCompetitor().isPublishData()) {
			dto.setFirstName(participant.getCompetitor().getFirstName());
			dto.setLastName(participant.getCompetitor().getLastName());
		} else {
			dto.setFirstName("******");
			dto.setLastName("******");
		}
		return dto;
	};

	@Override
	public ParticipantsInfoDto convert(List<Participant> participants) {
		ParticipantsInfoDto compParticipantInfoDto = new ParticipantsInfoDto();
		compParticipantInfoDto.setParticipants(convertParticipantDtos(participants));
		return compParticipantInfoDto;
	}

	private List<ParticipantDto> convertParticipantDtos(List<Participant> participants) {
		return participants.stream()
			.map(convertParticipant)
			.collect(Collectors.toList());
	}
}
