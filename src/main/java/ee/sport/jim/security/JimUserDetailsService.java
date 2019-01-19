package ee.sport.jim.security;

import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JimUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public JimUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(final String userNameOrEmail) throws UsernameNotFoundException {
		log.debug("Authenticating {}", userNameOrEmail);
		return userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
			.map(user -> createSpringSecurityUser(userNameOrEmail, user))
			.orElseThrow(() -> new UsernameNotFoundException("User with username or email " + userNameOrEmail + " was not found in the database"));
	}

	private org.springframework.security.core.userdetails.User createSpringSecurityUser(String userNameOrEmail, User user) {
		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
			.map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
			.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUserName(),
			user.getPassword(), grantedAuthorities);
	}
}
