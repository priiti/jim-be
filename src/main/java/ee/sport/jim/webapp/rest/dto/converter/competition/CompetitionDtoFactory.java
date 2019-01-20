package ee.sport.jim.webapp.rest.dto.converter.competition;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.rest.dto.competition.ChampionshipTypeDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDistanceDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class CompetitionDtoFactory {
	private final CompetitionConverter competitionConverter;
	private final CompetitionDistanceConverter competitionDistanceConverter;
	private final CompetitionPriceConverter competitionPriceConverter;
	private final ChampionshipTypeConverter championshipTypeConverter;

	@Autowired
	public CompetitionDtoFactory(CompetitionConverter competitionConverter,
															 CompetitionDistanceConverter competitionDistanceConverter,
															 CompetitionPriceConverter competitionPriceConverter,
															 ChampionshipTypeConverter championshipTypeConverter) {
		this.competitionConverter = competitionConverter;
		this.competitionDistanceConverter = competitionDistanceConverter;
		this.competitionPriceConverter = competitionPriceConverter;
		this.championshipTypeConverter = championshipTypeConverter;
	}

	public CompetitionDto convertCompetition(Competition competition) {
		CompetitionDto competitionDto = competitionConverter.convertEntity(competition);
		competitionDto.setDistances(convertCompetitionDistance(competition));
		return competitionDto;
	}

	private List<CompetitionDistanceDto> convertCompetitionDistance(Competition competition) {
		List<CompetitionDistanceDto> distances = new ArrayList<>();
		for (CompetitionDistance distance : competition.getDistances()) {
			ChampionshipTypeDto championshipTypeDto = championshipTypeConverter.convertEntity(distance.getChampionshipType());
			List<CompetitionPriceDto> competitionPriceDtos = competitionPriceConverter.convertEntity(new ArrayList<>(distance.getPrices()));
			CompetitionDistanceDto competitionDistanceDto = competitionDistanceConverter.convertEntity(distance);
			distances.add(CompetitionDistanceDto.buildCompetitionDistanceDto(competitionDistanceDto, championshipTypeDto, competitionPriceDtos));
		}
		return distances;
	}
}
