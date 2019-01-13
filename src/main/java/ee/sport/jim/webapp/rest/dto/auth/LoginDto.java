package ee.sport.jim.webapp.rest.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
	@NotBlank
	private String userNameOrEmail;
	@NotBlank
	private String password;
}
