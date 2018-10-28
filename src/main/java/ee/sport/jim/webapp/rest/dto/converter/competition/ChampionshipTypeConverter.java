package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.ChampionshipType;
import ee.sport.jim.webapp.rest.dto.competition.ChampionshipTypeDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

public class ChampionshipTypeConverter extends GenericConverter<ChampionshipType, ChampionshipTypeDto> {
	@Override
	public ChampionshipTypeDto convertEntity(ChampionshipType entity) {
		ChampionshipTypeDto dto = new ChampionshipTypeDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public ChampionshipType convertDto(ChampionshipTypeDto dto) {
		throw new NotImplementedException("ChampionshipType entity converter not implemented");
	}
}
