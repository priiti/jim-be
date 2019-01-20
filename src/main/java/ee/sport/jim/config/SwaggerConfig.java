package ee.sport.jim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private final JimReleaseProperties jimReleaseProperties;

	@Value("${swagger.api.title}")
	private String swaggerApiTitle;

	@Value("${swagger.api.description}")
	private String swaggerApiDescription;

	public SwaggerConfig(JimReleaseProperties jimReleaseProperties) {
		this.jimReleaseProperties = jimReleaseProperties;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.directModelSubstitute(LocalDate.class, String.class)
			.directModelSubstitute(LocalDateTime.class, String.class)
			.select()
			.apis(RequestHandlerSelectors.basePackage("ee.sport.jim"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(new ApiInfo(
				swaggerApiTitle,
				swaggerApiDescription,
				jimReleaseProperties.getReleaseVersion(),
				null,
				null,
				null,
				null,
				new ArrayList<>()
			));
	}
}
