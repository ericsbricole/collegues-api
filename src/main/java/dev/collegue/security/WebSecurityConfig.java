package dev.collegue.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.collegue.filter.JWTAuthorizationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	JWTAuthorizationFilter jwtAuthorationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	public WebSecurityConfig() {
	}
	
	public WebSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		
		.cors() .and().authorizeRequests()

				.antMatchers(HttpMethod.GET, "/collegues/**").hasRole("USER")
				.antMatchers(HttpMethod.PATCH, "/collegues/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/collegues/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/auth").permitAll()

				.antMatchers("/h2-console/**").permitAll()

				.and().addFilterBefore(jwtAuthorationFilter, UsernamePasswordAuthenticationFilter.class)

				.logout().logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
				.deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion
	}

}
