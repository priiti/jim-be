package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CompetitionConverter extends GenericConverter<Competition, CompetitionDto> {

	@Override
	public CompetitionDto convertEntity(Competition entity) {
		CompetitionDto dto = new CompetitionDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setDescription(entity.getDescription());
		dto.setAddress(entity.getAddress());
		return dto;
	}

	@Override
	public Competition convertDto(CompetitionDto dto) {
		throw new NotImplementedException("Competition entity converter not implemented");
	}

	@Override
	public List<CompetitionDto> convertEntity(List<Competition> entities) {
		return super.convertEntity(entities);
	}
}
