package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class CompetitionConverter extends GenericConverter<Competition, CompetitionDto> {
	private static final CompetitionDistanceConverter competitionDistanceConverter = new CompetitionDistanceConverter();

	@Override
	public CompetitionDto convertEntity(Competition entity) {
		CompetitionDto dto = new CompetitionDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setDescription(entity.getDescription());
		dto.setAddress(entity.getAddress());
		dto.setDistances(competitionDistanceConverter.convertEntity(entity.getDistances()));
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
