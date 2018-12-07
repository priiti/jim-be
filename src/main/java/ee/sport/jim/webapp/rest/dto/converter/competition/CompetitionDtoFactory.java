package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompDistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantsInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class CompetitionDtoFactory {
	private final CompetitionConverter competitionConverter;
	private final CompetitionDistanceConverter distanceConverter;
	private final DistanceInfoConverter distanceInfoConverter;
	private final ParticipantPublicInfoConverter participantInfoConverter;
	private final ParticipantPrivateInfoConverter participantPrivateInfoConverter;

	@Autowired
	public CompetitionDtoFactory(CompetitionConverter competitionConverter, CompetitionDistanceConverter distanceConverter,
															 ParticipantPublicInfoConverter participantInfoConverter,
															 DistanceInfoConverter distanceInfoConverter,
															 ParticipantPrivateInfoConverter participantPrivateInfoConverter) {
		this.competitionConverter = competitionConverter;
		this.distanceConverter = distanceConverter;
		this.participantInfoConverter = participantInfoConverter;
		this.distanceInfoConverter = distanceInfoConverter;
		this.participantPrivateInfoConverter = participantPrivateInfoConverter;
	}

	public CompetitionDto getCompetitionForRegistrationDto(Competition competition) {
		CompetitionDto competitionDto = competitionConverter.convertEntity(competition);
		competitionDto.setDistances(distanceConverter.convertEntity(new ArrayList<>(competition.getDistances())));
		return competitionDto;
	}

	public ParticipantsInfoDto getPublicCompParticipantsInfo(List<Participant> participants) {
		return participantInfoConverter.convert(participants);
	}

	public ParticipantsInfoDto getPrivateCompParticipantsInfo(List<Participant> participants) {
		return participantPrivateInfoConverter.convert(participants);
	}

	public CompDistanceInfoDto getCompetitionDistancesInfo(Competition competition) {
		return distanceInfoConverter.convert(competition);
	}
}
