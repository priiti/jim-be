package ee.sport.jim.webapp.rest.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class URIBuilder {

	public static URI create(String path, Object... uriVariableValues) {
		return ServletUriComponentsBuilder
			.fromCurrentContextPath().path(path)
			.buildAndExpand(uriVariableValues).toUri();
	}
}
