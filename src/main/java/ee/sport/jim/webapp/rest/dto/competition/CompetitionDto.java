package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionDto {
	private Long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private String address;
	private List<CompetitionDistanceDto> competitionDistances = new ArrayList<>();
}
