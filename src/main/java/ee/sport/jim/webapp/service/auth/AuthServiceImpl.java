package ee.sport.jim.webapp.service.auth;

import ee.sport.jim.security.JwtTokenProvider;
import ee.sport.jim.webapp.domain.Role;
import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.repository.RoleRepository;
import ee.sport.jim.webapp.repository.UserRepository;
import ee.sport.jim.webapp.rest.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static ee.sport.jim.webapp.domain.RoleName.ROLE_ORGANIZER;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;

	@Autowired
	public AuthServiceImpl(UserRepository userRepository,
												 AuthenticationManager authenticationManager,
												 RoleRepository roleRepository,
												 PasswordEncoder passwordEncoder,
												 JwtTokenProvider tokenProvider) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User register(User user) {
		log.info("Starting user registration: " + user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName(ROLE_ORGANIZER)
			.orElseThrow(() -> new AppException("User role not set."));
		user.setRoles(Collections.singleton(role));
		return userRepository.save(user);
	}

	@Override
	public String authenticateUser(final UsernamePasswordAuthenticationToken authenticationToken) {
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return tokenProvider.generateToken(authentication);
	}
}
