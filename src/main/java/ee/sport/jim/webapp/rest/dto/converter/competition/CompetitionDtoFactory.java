package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class CompetitionDtoFactory {
	private final CompetitionConverter competitionConverter;
	private final CompetitionDistanceConverter distanceConverter;
	private final DistanceInfoConverter distanceInfoConverter;
	private final ParticipantInfoConverter participantInformationConverter = new ParticipantInfoConverter();

	@Autowired
	public CompetitionDtoFactory(CompetitionConverter competitionConverter,
															 CompetitionDistanceConverter distanceConverter,
															 DistanceInfoConverter distanceInfoConverter) {
		this.competitionConverter = competitionConverter;
		this.distanceConverter = distanceConverter;
		this.distanceInfoConverter = distanceInfoConverter;
	}

	public CompetitionDto getCompetitionForRegistrationDto(Competition competition) {
		CompetitionDto competitionDto = competitionConverter.convertEntity(competition);
		competitionDto.setDistances(distanceConverter.convertEntity(new ArrayList<>(competition.getDistances())));
		return competitionDto;
	}

	public CompDistanceInfoDto getCompetitionDistancesInfo(Competition competition) {
		return distanceInfoConverter.convert(competition);
	}

	public List<ParticipantDto> getParticipantInformation(List<Participant> participants, final boolean hasRole) {
		return participantInformationConverter.convertEntity(participants, hasRole);
	}
}
