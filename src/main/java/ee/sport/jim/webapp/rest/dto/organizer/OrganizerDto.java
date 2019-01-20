package ee.sport.jim.webapp.rest.dto.organizer;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.rest.dto.competition.CompetitionDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrganizerDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private List<CompetitionDto> competitions;
}
