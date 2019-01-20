package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.ChampionshipType;
import ee.sport.jim.webapp.rest.dto.competition.ChampionshipTypeDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.springframework.stereotype.Component;

@Component
public final class ChampionshipTypeConverter extends GenericConverter<ChampionshipType, ChampionshipTypeDto> {

	@Override
	public ChampionshipTypeDto convertEntity(ChampionshipType entity) {
		if (entity == null) {
			return null;
		}
		ChampionshipTypeDto dto = new ChampionshipTypeDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public ChampionshipType convertDto(ChampionshipTypeDto dto) {
		ChampionshipType entity = new ChampionshipType();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
