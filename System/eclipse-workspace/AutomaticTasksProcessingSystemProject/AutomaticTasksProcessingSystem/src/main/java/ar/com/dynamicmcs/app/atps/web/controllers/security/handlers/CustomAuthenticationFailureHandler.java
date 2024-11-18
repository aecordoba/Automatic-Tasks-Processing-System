/*
 * 		CustomAuthenticationFailureHandler.java
 *   Copyright (C) 2024  Adrián E. Córdoba [software.dynamicmcs@gmail.com]
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

package ar.com.dynamicmcs.app.atps.web.controllers.security.handlers;

import java.io.IOException;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationFailureHandler#onAuthenticationFailure(jakarta.servlet.http
	 * .HttpServletRequest, jakarta.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (exception.getCause() instanceof LockedException) {
			defaultRedirectStrategy.sendRedirect(request, response, "/login-locked");
			return;
		}
		defaultRedirectStrategy.sendRedirect(request, response, "/login-error");
	}

}
