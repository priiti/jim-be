package ee.sport.jim.system;

import ee.sport.jim.config.JimReleaseProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/public/config")
public class MonitoringController {
	private final JimReleaseProperties jimReleaseProperties;

	public MonitoringController(JimReleaseProperties jimReleaseProperties) {
		this.jimReleaseProperties = jimReleaseProperties;
	}

	@GetMapping(value = "/version", produces = APPLICATION_JSON_VALUE)
	public Map<String, String> getVersion() {
		Map<String, String> versionResponse = new HashMap<>();
		versionResponse.put("version", jimReleaseProperties.getReleaseVersion());
		return versionResponse;
	}
}
