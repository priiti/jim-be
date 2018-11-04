package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
}
