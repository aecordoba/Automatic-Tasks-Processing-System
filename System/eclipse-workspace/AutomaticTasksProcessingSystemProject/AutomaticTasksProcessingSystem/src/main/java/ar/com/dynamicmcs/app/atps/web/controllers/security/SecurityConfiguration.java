/*
 * 		SecurityConfiguration.java						Nov 17, 2024
 *					Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 *
 *   Copyright (C) 2024
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ar.com.dynamicmcs.app.atps.web.controllers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ar.com.dynamicmcs.app.atps.web.controllers.security.handlers.CustomAuthenticationFailureHandler;
import ar.com.dynamicmcs.app.atps.web.controllers.security.handlers.CustomLogoutSuccessHandler;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/user-register").hasAuthority("ADMIN")
				.requestMatchers("/sysinternals/**").hasAnyAuthority("ADMIN", "USER")
				.requestMatchers("/system-control", "/state-change").hasAnyAuthority("ADMIN", "USER", "OBSERVER")
				.requestMatchers("/", "/images/**", "/styles/**", "/scripts/**").permitAll())
				.formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("user")
						.passwordParameter("password").failureHandler(customAuthenticationFailureHandler)
						.defaultSuccessUrl("/", true).permitAll())
				.logout(logout -> logout.logoutSuccessHandler(customLogoutSuccessHandler).logoutSuccessUrl("/"))
				.exceptionHandling(exception -> exception.accessDeniedPage("/"));

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
