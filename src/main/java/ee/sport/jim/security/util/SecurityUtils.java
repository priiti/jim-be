package ee.sport.jim.security.util;

import ee.sport.jim.security.UserPrincipal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

	public static UserPrincipal getCurrentUser() {
		return isAuthenticationPresent() ? (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
	}

	public static boolean hasPrivilege(String privilege) {
		return getPrivileges().contains(privilege);
	}

	public static boolean hasAnyPrivilege(String... privileges) {
		return hasAnyPrivilege(Arrays.asList(privileges));
	}

	private static boolean hasAnyPrivilege(Collection privileges) {
		return CollectionUtils.containsAny(getPrivileges(), privileges);
	}

	private static List<String> getPrivileges() {
		UserPrincipal principal = getCurrentUser();
		if (principal == null || CollectionUtils.isEmpty(principal.getAuthorities())) {
			return Collections.emptyList();
		}
		return principal.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());
	}

	public static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public static boolean isAuthenticationPresent() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context != null && context.getAuthentication() != null && context.getAuthentication().getAuthorities() instanceof UserPrincipal;
	}
}
