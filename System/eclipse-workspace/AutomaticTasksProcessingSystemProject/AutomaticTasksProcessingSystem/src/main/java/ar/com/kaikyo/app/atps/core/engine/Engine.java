/*
 * 		Engine.java
 *   Copyright (C) 2024  Adrián E. Córdoba [software.asia@gmail.com]
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

/**
 * 		Engine.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 6, 2024
 */
package ar.com.kaikyo.app.atps.core.engine;

import org.springframework.stereotype.Component;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class Engine {
	private boolean stateTransition;

	/**
	*
	*/
	public Engine() {
		stateTransition = false;
	}

	public void start() {
		stateTransition = true;

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stateTransition = false;
	}

	public void stop() {
		stateTransition = true;

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stateTransition = false;
	}

	/**
	 * @return the stateTransition
	 */
	public boolean isStateTransition() {
		return stateTransition;
	}
}
