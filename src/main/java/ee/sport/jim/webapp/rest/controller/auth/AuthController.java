package ee.sport.jim.webapp.rest.controller.auth;

import ee.sport.jim.security.JwtTokenProvider;
import ee.sport.jim.webapp.domain.Role;
import ee.sport.jim.webapp.domain.RoleName;
import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.repository.RoleRepository;
import ee.sport.jim.webapp.repository.UserRepository;
import ee.sport.jim.webapp.rest.dto.ApiResponse;
import ee.sport.jim.webapp.rest.dto.auth.JwtAuthenticationResponse;
import ee.sport.jim.webapp.rest.dto.auth.LoginDto;
import ee.sport.jim.webapp.rest.dto.auth.RegistrationDto;
import ee.sport.jim.webapp.rest.exception.AppException;
import ee.sport.jim.webapp.rest.util.URIBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;

	public AuthController(AuthenticationManager authenticationManager,
												UserRepository userRepository,
												RoleRepository roleRepository,
												PasswordEncoder passwordEncoder,
												JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody final LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginDto.getUserNameOrEmail(),
				loginDto.getPassword()
			)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody final RegistrationDto registrationDto) {
		if (userRepository.existsByUserName(registrationDto.getUserName())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken."), BAD_REQUEST);
		}
		if (userRepository.existsByEmail(registrationDto.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email is already taken."), BAD_REQUEST);
		}
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setUserName(registrationDto.getUserName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role role = roleRepository.findByName(RoleName.ROLE_ORGANIZER)
			.orElseThrow(() -> new AppException("User role not set."));
		user.setRoles(Collections.singleton(role));
		User result = userRepository.save(user);

		URI location = URIBuilder.create("/api/v1/users/{userName}", result.getUserName());
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully."));
	}
}
