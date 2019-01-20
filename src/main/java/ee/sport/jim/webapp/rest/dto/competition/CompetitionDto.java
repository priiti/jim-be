package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CompetitionDto {
	private Long id;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String description;
	private String address;
	private List<CompetitionDistanceDto> distances;
}
