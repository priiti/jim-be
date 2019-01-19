package ee.sport.jim.webapp.rest.controller.auth;

import ee.sport.jim.security.jwt.JwtAuthenticationFilter;
import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.auth.JwtToken;
import ee.sport.jim.webapp.rest.dto.auth.LoginDto;
import ee.sport.jim.webapp.rest.dto.auth.RegistrationDto;
import ee.sport.jim.webapp.rest.util.URIBuilder;
import ee.sport.jim.webapp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/auth/public")
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/authenticate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtToken> authenticateUser(@Valid @RequestBody final LoginDto loginDto) {
		final String jwt = authService.authenticateUser(
			new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword()));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody final RegistrationDto registrationDto) {
		if (authService.existsByUserName(registrationDto.getUserName())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken."), BAD_REQUEST);
		}
		if (authService.existsByEmail(registrationDto.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email is already taken."), BAD_REQUEST);
		}
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setUserName(registrationDto.getUserName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		User result = authService.register(user);

		URI location = URIBuilder.create("/api/v1/users/{userName}", result.getUserName());
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully."));
	}
}
