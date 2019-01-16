package ee.sport.jim.webapp.rest.util;

import ee.sport.jim.webapp.domain.competitor.Participant;

public class CompetitorUtil {

	public static String getFullName(Participant participant) {
		return String.format("%s %s", participant.getCompetitor().getFirstName(), participant.getCompetitor().getLastName());
	}
}
