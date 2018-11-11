package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.ChampionshipType;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CompetitionDistanceConverter extends GenericConverter<CompetitionDistance, CompetitionDistanceDto> {
	private final CompetitionPriceConverter competitionPriceConverter;
	private final ChampionshipTypeConverter championshipTypeConverter;

	public CompetitionDistanceConverter(CompetitionPriceConverter competitionPriceConverter,
																			ChampionshipTypeConverter championshipTypeConverter) {
		this.competitionPriceConverter = competitionPriceConverter;
		this.championshipTypeConverter = championshipTypeConverter;
	}

	@Override
	public CompetitionDistanceDto convertEntity(CompetitionDistance entity) {
		CompetitionDistanceDto dto = new CompetitionDistanceDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setLength(entity.getLength());
		dto.setSpecialNotes(entity.getSpecialNotes());
		dto.setStartTime(entity.getStartTime());
		dto.setStartNumbering(entity.getStartNumbering());
		Optional<ChampionshipType> optionalType = Optional.ofNullable(entity.getChampionshipType());
		optionalType.ifPresent(type -> dto.setChampionshipType(championshipTypeConverter.convertEntity(type)));
		dto.setPrices(competitionPriceConverter.convertEntity(new ArrayList<>(entity.getPrices())));
		return dto;
	}

	@Override
	public CompetitionDistance convertDto(CompetitionDistanceDto dto) {
		throw new NotImplementedException("CompetitionDistance entity converter not implemented");
	}

	@Override
	public List<CompetitionDistanceDto> convertEntity(List<CompetitionDistance> entities) {
		return super.convertEntity(entities);
	}
}
