package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompetitionDtoFactory {
	private static final CompetitionConverter competitionConverter = new CompetitionConverter();

	public static CompetitionDto getCompetitionDtoForRegistration(Competition competition) {
		return competitionConverter.convertEntity(competition);
	}
}
