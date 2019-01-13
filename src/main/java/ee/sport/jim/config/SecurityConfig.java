package ee.sport.jim.config;

import ee.sport.jim.security.CustomUserDetailsService;
import ee.sport.jim.security.JwtAuthenticationEntryPoint;
import ee.sport.jim.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final CustomUserDetailsService customUserDetailsService;
	private final JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
												JwtAuthenticationEntryPoint unauthorizedHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
			.csrf()
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers(
					"/",
					"/favicon.ico",
					"/**/*.png",
					"/**/*.gif",
					"/**/*.svg",
					"/**/*.jpg",
					"/**/*.html",
					"/**/*.css",
					"/**/*.js"
				)
				.permitAll()
			.antMatchers("/api/v1/**")
				.permitAll()
			.antMatchers("/h2-console/**", "/register", "/console")
				.permitAll()
//			.antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
//				.permitAll()
			.antMatchers(GET, "/api/polls/**", "/api/users/**")
				.permitAll()
			.anyRequest()
			.authenticated();

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
