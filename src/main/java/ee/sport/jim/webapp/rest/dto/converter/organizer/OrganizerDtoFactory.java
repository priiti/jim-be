package ee.sport.jim.webapp.rest.dto.converter.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.rest.dto.converter.competition.CompetitionConverter;
import ee.sport.jim.webapp.rest.dto.organizer.OrganizerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public final class OrganizerDtoFactory {
	private final OrganizerConverter organizerConverter;
	private final CompetitionConverter competitionConverter;

	@Autowired
	public OrganizerDtoFactory(OrganizerConverter organizerConverter,
														 CompetitionConverter competitionConverter) {
		this.organizerConverter = organizerConverter;
		this.competitionConverter = competitionConverter;
	}

	public OrganizerDto convertOrganizer(Organizer organizer) {
		OrganizerDto organizerDto = organizerConverter.convertEntity(organizer);
		organizerDto.setCompetitions(competitionConverter.convertEntity(new ArrayList<>(organizer.getCompetitions())));
		return organizerDto;
	}
}
