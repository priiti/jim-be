package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competition.CompParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.DistanceInfoDto;
import ee.sport.jim.webapp.rest.dto.competition.ParticipantInfoDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CompParticipantInfoConverter extends GenericConverter<Competition, CompParticipantInfoDto> {
	private final CompetitionConverter competitionConverter;
	private final CompetitionDistanceConverter distanceConverter;

	public CompParticipantInfoConverter(CompetitionConverter competitionConverter, CompetitionDistanceConverter distanceConverter) {
		this.competitionConverter = competitionConverter;
		this.distanceConverter = distanceConverter;
	}

	@Override
	public CompParticipantInfoDto convertEntity(Competition entity) {
		CompParticipantInfoDto dto = new CompParticipantInfoDto();
		dto.setCompetition(competitionConverter.convertEntity(entity));
		dto.setDistanceInfo(convertDistanceInfo(entity));
		return dto;
	}

	private List<DistanceInfoDto> convertDistanceInfo(Competition entity) {
		List<DistanceInfoDto> distanceInfo = new ArrayList<>();
		for (CompetitionDistance competitionDistance : entity.getDistances()) {
			DistanceInfoDto infoDto = new DistanceInfoDto();
			infoDto.setDistance(distanceConverter.convertEntity(competitionDistance));
			infoDto.setParticipants(convertParticipants(competitionDistance.getParticipants()));
			distanceInfo.add(infoDto);
		}
		return distanceInfo;
	}

	private List<ParticipantInfoDto> convertParticipants(Set<Participant> participants) {
		List<ParticipantInfoDto> participantInfo = new ArrayList<>();
		for (Participant participant : participants) {
			ParticipantInfoDto dto = new ParticipantInfoDto();
			dto.setFirstName(participant.getCompetitor().getFirstName());
			dto.setLastName(participant.getCompetitor().getLastName());
			dto.setParticipantId(participant.getId());
			dto.setCompetitorNumber(participant.getCompetitorNumber());
			participantInfo.add(dto);
		}
		return participantInfo;
	}


	@Override
	public Competition convertDto(CompParticipantInfoDto dto) {
		return null;
	}
}
