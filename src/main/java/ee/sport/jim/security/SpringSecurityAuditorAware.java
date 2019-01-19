package ee.sport.jim.security;

import ee.sport.jim.security.util.SecurityUtils;
import ee.sport.jim.system.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(SecurityUtils.getCurrentUserLogin()
			.orElse(Constants.SYSTEM_ACCOUNT));
	}
}
