package ee.sport.jim.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class JimReleaseProperties {
	@Value("app.release.version")
	private String releaseVersion;
}
