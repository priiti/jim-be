package ee.sport.jim.config;

import ee.sport.jim.security.JimUserDetailsService;
import ee.sport.jim.security.JwtAuthenticationEntryPoint;
import ee.sport.jim.security.jwt.JwtAuthenticationFilter;
import ee.sport.jim.security.jwt.JwtConfigurer;
import ee.sport.jim.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JimUserDetailsService jimUserDetailsService;
	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final JwtTokenProvider tokenProvider;
	private final SecurityProblemSupport problemSupport;

	@Autowired
	public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
															 JimUserDetailsService jimUserDetailsService,
															 JwtAuthenticationEntryPoint unauthorizedHandler,
															 JwtTokenProvider tokenProvider,
															 SecurityProblemSupport problemSupport) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.jimUserDetailsService = jimUserDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.tokenProvider = tokenProvider;
		this.problemSupport = problemSupport;
	}

	@PostConstruct
	public void init() {
		try {
			authenticationManagerBuilder
				.userDetailsService(jimUserDetailsService)
				.passwordEncoder(passwordEncoder());
		} catch (Exception ex) {
			throw new BeanInitializationException("Security configuration failed", ex);
		}
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(tokenProvider);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(HttpMethod.OPTIONS, "/**")
			.antMatchers("/app/**/*.{js,html}")
			.antMatchers("/content/**")
			.antMatchers("/h2-console/**")
			.antMatchers("/swagger-ui/index.html")
			.antMatchers("/test/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
//			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.cors()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
			.accessDeniedHandler(problemSupport)
			.and()
			.headers()
			.frameOptions()
			.disable()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(
				"/api/v1/competition/public/**",
				"/api/v1/organizer/public/**",
				"/api/v1/competitor/public/**",
				"/api/v1/auth/public/**",
				"/public/config/version"
			).permitAll()
			.antMatchers("/api/v1/competition/private/**")
				.authenticated()
			.and()
			.apply(securityConfigurerAdapter())
			.and()
			.requiresChannel()
			.requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
			.requiresSecure();
	}

	private JwtConfigurer securityConfigurerAdapter() {
		return new JwtConfigurer(tokenProvider);
	}
}
