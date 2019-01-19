package ee.sport.jim.webapp.rest.dto.competition;

import ee.sport.jim.webapp.domain.competition.ChampionshipType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChampionshipTypeDto {
	private Long id;
	private String name;
}
