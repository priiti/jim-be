package ee.sport.jim.webapp.rest.dto.competitor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ee.sport.jim.webapp.domain.shared.Gender;
import ee.sport.jim.webapp.rest.util.GenderSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
	fieldVisibility = JsonAutoDetect.Visibility.ANY,
	creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class CompetitorDto {
	private Long id;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String email;
	@NotNull
	private String phoneNumber;
	@NotNull
	@JsonSerialize(using = GenderSerializer.class)
	private Gender gender;
	@NotNull
	private LocalDateTime dateOfBirth;
	private String sportsClub;
	@NotNull
	private boolean newsLetterSubscription;
	@NotNull
	private boolean publishData;
}
