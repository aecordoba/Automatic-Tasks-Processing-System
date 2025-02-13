/*
 * 		AuthenticationFailureEventListener.java
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

package ar.com.dynamicmcs.app.atps.web.security.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import ar.com.dynamicmcs.app.atps.data.services.LoginAttemptsService;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class AuthenticationFailureEventListener
		implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private LoginAttemptsService loginAttemptsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		String username = (String) event.getAuthentication().getPrincipal();
		loginAttemptsService.loginFailed(username);
	}

}
