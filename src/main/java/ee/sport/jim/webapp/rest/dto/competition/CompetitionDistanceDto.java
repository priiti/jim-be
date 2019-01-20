package ee.sport.jim.webapp.rest.dto.competition;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
	fieldVisibility = JsonAutoDetect.Visibility.ANY,
	creatorVisibility = JsonAutoDetect.Visibility.ANY
)
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
	private Long participantCount;
	private Long paidParticipantCount;
	private Long nonPaidParticipantCount;
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
			.participantCount(competitionDistanceDto.participantCount)
			.paidParticipantCount(competitionDistanceDto.paidParticipantCount)
			.nonPaidParticipantCount(competitionDistanceDto.nonPaidParticipantCount)
			.build();
	}
}
