package ee.sport.jim.webapp.service;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Competitor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompetitionUtils {

	public static String getCompetitorFullName(Competitor competitor) {
		return String.format("%s %s", competitor.getFirstName(), competitor.getLastName());
	}

	public static String getCompetitionDistanceName(CompetitionDistance distance) {
		return String.format("Competition: %s, distance: %s", distance.getCompetition().getName(), distance.getName());
	}
}
