package ee.sport.jim.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityContext {

	public static boolean hasRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(role));
		}
		return false;
	}
}
