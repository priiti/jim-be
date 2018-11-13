package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class CompetitionDtoFactory {
	private final CompetitionConverter competitionConverter;
	private final CompetitionDistanceConverter distanceConverter;
	private final CompParticipantInfoConverter participantInfoConverter;

	public CompetitionDtoFactory(CompetitionConverter competitionConverter, CompetitionDistanceConverter distanceConverter,
															 CompParticipantInfoConverter participantInfoConverter) {
		this.competitionConverter = competitionConverter;
		this.distanceConverter = distanceConverter;
		this.participantInfoConverter = participantInfoConverter;
	}

	public CompetitionDto getCompetitionForRegistrationDto(Competition competition) {
		CompetitionDto competitionDto = competitionConverter.convertEntity(competition);
		competitionDto.setDistances(distanceConverter.convertEntity(new ArrayList<>(competition.getDistances())));
		return competitionDto;
	}

	public CompParticipantInfoDto getCompetitionParticipantsInfo(List<Participant> participants) {
		return participantInfoConverter.convert(participants);
	}
}
