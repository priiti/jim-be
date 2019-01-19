package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.rest.dto.competition.ChampionshipTypeDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionPriceDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class CompetitionDistanceConverter extends GenericConverter<CompetitionDistance, CompetitionDistanceDto> {
	private final CompetitionPriceConverter competitionPriceConverter;
	private final ChampionshipTypeConverter championshipTypeConverter;
	private final CompetitionConverter competitionConverter;

	@Autowired
	public CompetitionDistanceConverter(CompetitionPriceConverter competitionPriceConverter,
																			ChampionshipTypeConverter championshipTypeConverter, CompetitionConverter competitionConverter) {
		this.competitionPriceConverter = competitionPriceConverter;
		this.championshipTypeConverter = championshipTypeConverter;
		this.competitionConverter = competitionConverter;
	}

	@Override
	public CompetitionDistanceDto convertEntity(CompetitionDistance entity) {
		return CompetitionDistanceDto.builder()
			.id(entity.getId())
			.name(entity.getName())
			.length(entity.getLength())
			.specialNotes(entity.getSpecialNotes())
			.startTime(entity.getStartTime())
			.startNumbering(entity.getStartNumbering())
			.championshipType(getChampionShipType(entity))
			.prices(getCompetitionPrices(entity))
			.competition(competitionConverter.convertEntity(entity.getCompetition()))
			.build();
	}

	private ChampionshipTypeDto getChampionShipType(CompetitionDistance competitionDistance) {
		return competitionDistance.getChampionshipType() != null ?
			championshipTypeConverter.convertEntity(competitionDistance.getChampionshipType()) : null;
	}

	private List<CompetitionPriceDto> getCompetitionPrices(CompetitionDistance competitionDistance) {
		return competitionDistance.getPrices().stream()
			.map(competitionPriceConverter::convertEntity)
			.collect(Collectors.toList());
	}

	@Override
	public CompetitionDistance convertDto(CompetitionDistanceDto dto) {
		CompetitionDistance entity = new CompetitionDistance();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setLength(dto.getLength());
		entity.setSpecialNotes(dto.getSpecialNotes());
		entity.setStartTime(dto.getStartTime());
		entity.setStartNumbering(dto.getStartNumbering());
		entity.setCurrentCompetitorNumber(dto.getCurrentCompetitorNumber());
		return entity;
	}

	@Override
	public List<CompetitionDistanceDto> convertEntity(List<CompetitionDistance> entities) {
		return super.convertEntity(entities);
	}
}
