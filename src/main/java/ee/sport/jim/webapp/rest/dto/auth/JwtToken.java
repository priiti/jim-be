package ee.sport.jim.webapp.rest.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtToken {
	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	public JwtToken setToken(String token) {
		this.token = token;
		return this;
	}
}
