package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.CompetitionPrice;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionPriceDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionPriceConverter extends GenericConverter<CompetitionPrice, CompetitionPriceDto> {
	@Override
	public CompetitionPriceDto convertEntity(CompetitionPrice entity) {
		CompetitionPriceDto dto = new CompetitionPriceDto();
		dto.setId(entity.getId());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setPrice(entity.getPrice());
		return dto;
	}

	@Override
	public CompetitionPrice convertDto(CompetitionPriceDto dto) {
		throw new NotImplementedException("CompetitionPrice entity converter not implemented");
	}

	@Override
	public List<CompetitionPriceDto> convertEntity(List<CompetitionPrice> entities) {
		return entities.stream()
			.map(this::convertEntity)
			.filter(competitionPriceDto -> competitionPriceDto.getEndDate().after(new Date()))
			.collect(Collectors.toList());
	}
}
