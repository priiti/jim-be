package ee.sport.jim.webapp.rest.controller.auth;

import ee.sport.jim.security.JwtTokenProvider;
import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.auth.JwtAuthenticationResponse;
import ee.sport.jim.webapp.rest.dto.auth.LoginDto;
import ee.sport.jim.webapp.rest.dto.auth.RegistrationDto;
import ee.sport.jim.webapp.rest.util.URIBuilder;
import ee.sport.jim.webapp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	private final AuthService authService;
	private final JwtTokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthController(AuthService authService, JwtTokenProvider tokenProvider,
												AuthenticationManager authenticationManager) {
		this.authService = authService;
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping(value = "/public/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody final LoginDto loginDto) {
//		final String jwt = authService.authenticateUser(
//			new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword()));
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping(value = "/public/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
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
