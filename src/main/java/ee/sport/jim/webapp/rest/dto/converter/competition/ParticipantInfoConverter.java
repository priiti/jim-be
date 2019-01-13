package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.system.core.BooleanHolder;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantDto;

import java.util.List;
import java.util.stream.Collectors;

public final class ParticipantInfoConverter {

	public ParticipantDto convertEntityPrivate(Participant entity) {
		ParticipantDto dto = new ParticipantDto();
		dto.setParticipantId(entity.getId());
		dto.setCompetitorNumber(entity.getCompetitorNumber());
		dto.setFirstName(entity.getCompetitor().getFirstName());
		dto.setLastName(entity.getCompetitor().getLastName());
		dto.setGender(entity.getCompetitor().getGender());
		dto.setEmail(entity.getCompetitor().getEmail());
		dto.setDateOfBirth(entity.getCompetitor().getDateOfBirth());
		dto.setSportsClub(entity.getCompetitor().getSportsClub());
		dto.setParticipationCount(entity.getParticipationCount());
		dto.setPaymentFulfilled(new BooleanHolder(entity.isPaymentFulfilled()));
		dto.setChampionshipParticipation(new BooleanHolder(entity.isChampionshipParticipation()));
		dto.setPublishData(new BooleanHolder(entity.getCompetitor().isPublishData()));
		dto.setNewsletterSubscription(new BooleanHolder(entity.getCompetitor().isNewsletterSubscription()));
		dto.setNumberPrinted(new BooleanHolder(entity.isNumberPrinted()));
		dto.setEnvelopePrinted(new BooleanHolder(entity.isEnvelopePrinted()));
		return dto;
	}

	public List<ParticipantDto> convertEntity(List<Participant> entities, final boolean hasRole) {
		return entities.stream()
			.map(participant -> hasRole ? convertEntityPrivate(participant) : convertEntityPublic(participant))
			.collect(Collectors.toList());
	}

	public ParticipantDto convertEntityPublic(Participant entity) {
		ParticipantDto dto = new ParticipantDto();
		dto.setParticipantId(entity.getId());
		dto.setCompetitorNumber(entity.getCompetitorNumber());
		if (!entity.getCompetitor().isPublishData()) {
			dto.setFirstName("******");
			dto.setLastName("******");
		} else {
			dto.setFirstName(entity.getCompetitor().getFirstName());
			dto.setLastName(entity.getCompetitor().getLastName());
		}
		return dto;
	}
}
