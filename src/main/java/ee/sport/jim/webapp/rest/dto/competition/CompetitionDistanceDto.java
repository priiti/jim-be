package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CompetitionDistanceDto {
	private Long id;
	private String name;
	private BigDecimal length;
	private Long championshipTypeId;
	private String specialNotes;
	private Long competitionId;
	private LocalDateTime startTime;
	private Integer startNumbering;
	private ChampionshipTypeDto championshipType;
	private List<CompetitionPriceDto> prices;
	private Integer participantCount;
	private Integer currentCompetitorNumber;
	private CompetitionDto competition;

	public static CompetitionDistanceDto buildCompetitionDistanceDto(CompetitionDistanceDto competitionDistanceDto,
																																	 ChampionshipTypeDto championshipTypeDto,
																																	 List<CompetitionPriceDto> competitionPriceDtos) {
		return CompetitionDistanceDto.builder()
			.id(competitionDistanceDto.getId())
			.name(competitionDistanceDto.getName())
			.length(competitionDistanceDto.getLength())
			.specialNotes(competitionDistanceDto.getSpecialNotes())
			.startTime(competitionDistanceDto.getStartTime())
			.startNumbering(competitionDistanceDto.getStartNumbering())
			.championshipType(championshipTypeDto)
			.prices(competitionPriceDtos)
			.build();
	}
}
