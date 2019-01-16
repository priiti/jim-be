package ee.sport.jim.webapp.service.auth;

import ee.sport.jim.webapp.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthService {

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

	User register(User user);

	String authenticateUser(UsernamePasswordAuthenticationToken authenticationToken);

}
