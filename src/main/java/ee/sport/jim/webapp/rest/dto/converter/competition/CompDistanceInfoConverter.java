package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class CompDistanceInfoConverter implements Converter<Competition, CompDistanceInfoDto> {
	private final CompetitionConverter competitionConverter;

	@Autowired
	public CompDistanceInfoConverter(CompetitionConverter competitionConverter) {
		this.competitionConverter = competitionConverter;
	}

	@Override
	public CompDistanceInfoDto convert(Competition competition) {
		CompDistanceInfoDto compDistanceInfoDto = new CompDistanceInfoDto();
		compDistanceInfoDto.setCompetition(competitionConverter.convertEntity(competition));
		compDistanceInfoDto.setDistances(convertCompetitionDistances(competition.getDistances()));
		return compDistanceInfoDto;
	}

	private List<CompetitionDistanceDto> convertCompetitionDistances(Set<CompetitionDistance> distances) {
		return distances.stream()
			.map(this::convertDistanceDto)
			.collect(Collectors.toList());
	}

	private CompetitionDistanceDto convertDistanceDto(CompetitionDistance distance) {
		CompetitionDistanceDto dto = new CompetitionDistanceDto();
		dto.setId(distance.getId());
		dto.setName(distance.getName());
		dto.setLength(distance.getLength());
		dto.setSpecialNotes(distance.getSpecialNotes());
		dto.setStartTime(distance.getStartTime());
		return dto;
	}
}
