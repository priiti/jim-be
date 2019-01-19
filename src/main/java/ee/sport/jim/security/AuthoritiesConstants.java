package ee.sport.jim.security;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class AuthoritiesConstants {

	public static final String ADMIN = "ROLE_ADMIN";

	public static final String ORGANIZER = "ROLE_ORGANIZER";

	public static final String COMPETITOR = "ROLE_COMPETITOR";

	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
