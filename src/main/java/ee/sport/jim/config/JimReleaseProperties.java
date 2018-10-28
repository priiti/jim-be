package ee.sport.jim.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:jim_release.properties")
public class JimReleaseProperties {
	@Value("release.version")
	private String releaseVersion;
}
