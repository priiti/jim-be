package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter extends GenericConverter<Participant, ParticipantDto> {

	@Override
	public ParticipantDto convertEntity(Participant entity) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Participant convertDto(ParticipantDto dto) {
		Participant entity = new Participant();
		entity.setId(dto.getParticipantId());
		entity.setParticipationCount(dto.getParticipationCount());
		entity.setChampionshipParticipation(dto.getChampionshipParticipation().isValue());
		entity.setPaymentFulfilled(dto.getPaymentFulfilled().isValue());
		entity.setNumberPrinted(dto.getNumberPrinted().isValue());
		entity.setEnvelopePrinted(dto.getEnvelopePrinted().isValue());
		entity.setCompetitorNumber(dto.getCompetitorNumber());
		entity.setChipId(dto.getChipId());
		return entity;
	}
}
