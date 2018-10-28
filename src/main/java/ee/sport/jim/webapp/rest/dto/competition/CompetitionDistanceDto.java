package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionDistanceDto {
	private Long id;
	private String name;
	private BigDecimal length;
	private String specialNotes;
	private Date startTime;
	private ChampionshipTypeDto championshipType;
	private List<CompetitionPriceDto> competitionPrices;
}
