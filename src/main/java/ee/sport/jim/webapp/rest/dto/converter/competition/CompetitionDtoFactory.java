package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompetitionDtoFactory {
	private static final CompetitionConverter competitionConverter = new CompetitionConverter();

	public static CompetitionDto getCompetitionDto(Competition competition) {
		return competitionConverter.convertEntity(competition);
	}

	public static List<CompetitionDto> getCompetitionDtos(List<Competition> competitions) {
		return competitionConverter.convertEntity(competitions);
	}
}
