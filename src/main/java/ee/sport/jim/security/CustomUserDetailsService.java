package ee.sport.jim.security;

import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with username or email:  + usernameOrEmail"));
		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return UserPrincipal.create(user);
	}
}
