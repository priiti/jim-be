package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantsInfoDto {
	private Long distanceParticipantCount;
	private List<ParticipantDto> participants;
}
