package com.cchacalcaje.cromartic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cchacalcaje.cromartic.security.auth.filter.JwtAuthenticationFilter;
import com.cchacalcaje.cromartic.security.auth.filter.JwtAuthorizationFilter;
import com.cchacalcaje.cromartic.security.auth.service.IJwtService;
import com.cchacalcaje.cromartic.security.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private IJwtService jwtService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity		
		.authorizeRequests().antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtService))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception{
		webSecurity
			.ignoring()
				.antMatchers(HttpMethod.GET, "/users", "/users/**", "/refresh-token")
			.and()
			.ignoring()
				.antMatchers("/error");
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userService)
		.passwordEncoder(passwordEncoder());
	}
}
