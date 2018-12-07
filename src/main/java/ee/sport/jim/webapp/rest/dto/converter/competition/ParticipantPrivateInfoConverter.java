package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantsInfoDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class ParticipantPrivateInfoConverter implements Converter<List<Participant>, ParticipantsInfoDto> {

	private Function<Participant, ParticipantDto> convertParticipantEntityToDto = participant -> {
		ParticipantDto dto = new ParticipantDto();
		dto.setParticipantId(participant.getId());
		dto.setCompetitorNumber(participant.getCompetitorNumber());
		dto.setFirstName(participant.getCompetitor().getFirstName());
		dto.setLastName(participant.getCompetitor().getLastName());
		dto.setEmail(participant.getCompetitor().getEmail());
		dto.setDateOfBirth(participant.getCompetitor().getDateOfBirth());
		dto.setSportsClub(participant.getCompetitor().getSportsClub());
		dto.setParticipationCount(participant.getParticipationCount());
		dto.setPaymentFulfilled(participant.isPaymentFulfilled());
		dto.setPublishData(participant.getCompetitor().isPublishData());
		dto.setNewsletterSubscription(participant.getCompetitor().isNewsletterSubscription());
		dto.setNumberPrinted(participant.isNumberPrinted());
		dto.setEnvelopePrinted(participant.isEnvelopePrinted());
		return dto;
	};

	@Override
	public ParticipantsInfoDto convert(List<Participant> source) {
		ParticipantsInfoDto participantsInfoDto = new ParticipantsInfoDto();
		participantsInfoDto.setParticipants(convertParticipantDtos(source));
		return participantsInfoDto;
	}

	private List<ParticipantDto> convertParticipantDtos(List<Participant> participants) {
		return participants.stream()
			.map(convertParticipantEntityToDto)
			.collect(Collectors.toList());
	}
}
