package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionDistanceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantConverter extends GenericConverter<Participant, ParticipantDto> {
	private final CompetitorConverter competitorConverter;
	private final CompetitionDistanceConverter competitionDistanceConverter;

	@Autowired
	public ParticipantConverter(CompetitorConverter competitorConverter,
															CompetitionDistanceConverter competitionDistanceConverter) {
		this.competitorConverter = competitorConverter;
		this.competitionDistanceConverter = competitionDistanceConverter;
	}

	@Override
	public ParticipantDto convertEntity(Participant entity) {
		return ParticipantDto.builder()
			.id(entity.getId())
			.participationCount(entity.getParticipationCount())
			.championshipParticipation(entity.isChampionshipParticipation())
			.paymentFulfilled(entity.isPaymentFulfilled())
			.numberPrinted(entity.isNumberPrinted())
			.envelopePrinted(entity.isEnvelopePrinted())
			.competitorNumber(entity.getCompetitorNumber())
			.chipId(entity.getChipId())
			.competitor(competitorConverter.convertEntity(entity.getCompetitor()))
			.competitionDistance(competitionDistanceConverter.convertEntity(entity.getCompetitionDistance()))
			.build();
	}

	@Override
	public Participant convertDto(ParticipantDto dto) {
		Participant entity = new Participant();
		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
		entity.setParticipationCount(dto.getParticipationCount());
		entity.setChampionshipParticipation(dto.isChampionshipParticipation());
		entity.setPaymentFulfilled(dto.isPaymentFulfilled());
		entity.setNumberPrinted(dto.isNumberPrinted());
		entity.setEnvelopePrinted(dto.isEnvelopePrinted());
		entity.setCompetitorNumber(dto.getCompetitorNumber());
		entity.setCompetitor(competitorConverter.convertDto(dto.getCompetitor()));
		entity.setChipId(dto.getChipId());
		return entity;
	}

	@Override
	public List<Participant> convertDto(List<ParticipantDto> dtos) {
		return super.convertDto(dtos);
	}

	@Override
	public List<ParticipantDto> convertEntity(List<Participant> entities) {
		return super.convertEntity(entities);
	}
}
