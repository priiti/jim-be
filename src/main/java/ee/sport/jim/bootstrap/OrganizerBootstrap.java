package ee.sport.jim.bootstrap;

import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrganizerBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private final AuthService authService;

	@Autowired
	public OrganizerBootstrap(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		registerOrganizer();
	}

	private void registerOrganizer() {
		User user = new User();
		user.setFirstName("Mart");
		user.setLastName("Walter");
		user.setUserName("martwalt");
		user.setEmail("Mart.Walter@jim.ee");
		user.setPassword("password");
		authService.register(user);
	}
}
