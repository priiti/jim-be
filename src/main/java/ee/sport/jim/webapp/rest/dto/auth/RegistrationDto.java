package ee.sport.jim.webapp.rest.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDto {
	@NotBlank
	@Size(min = 1, max = 40)
	private String firstName;

	@NotBlank
	@Size(min = 1, max = 40)
	private String lastName;

	@NotBlank
	@Size(min = 3, max = 15)
	private String userName;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 15)
	private String password;
}
