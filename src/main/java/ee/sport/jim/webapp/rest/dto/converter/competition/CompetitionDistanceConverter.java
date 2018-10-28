package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.CompetitionDistance;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class CompetitionDistanceConverter extends GenericConverter<CompetitionDistance, CompetitionDistanceDto> {
	private final CompetitionPriceConverter competitionPriceConverter = new CompetitionPriceConverter();
	private final ChampionshipTypeConverter championshipTypeConverter = new ChampionshipTypeConverter();

	@Override
	public CompetitionDistanceDto convertEntity(CompetitionDistance entity) {
		CompetitionDistanceDto distance = new CompetitionDistanceDto();
		distance.setId(entity.getId());
		distance.setName(entity.getName());
		distance.setLength(entity.getLength());
		distance.setSpecialNotes(entity.getSpecialNotes());
		distance.setStartTime(entity.getStartTime());
		if (entity.getChampionshipType() != null) {
			distance.setChampionshipType(championshipTypeConverter.convertEntity(entity.getChampionshipType()));
		}
		distance.setCompetitionPrices(competitionPriceConverter.convertEntity(new ArrayList<>(entity.getPrices())));
		return distance;
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
