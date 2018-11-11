package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantInfoDto {
	private Long participantId;
	private String firstName;
	private String lastName;
	private Integer competitorNumber;
}
