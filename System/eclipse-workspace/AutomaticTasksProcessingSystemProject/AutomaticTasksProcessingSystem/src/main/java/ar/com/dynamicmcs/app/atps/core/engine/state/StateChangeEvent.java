/*
 * 		StateChangeEvent.java						Apr 23, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine.state;

import org.springframework.context.ApplicationEvent;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class StateChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private State newState;

	/**
	 * 
	 * @param source
	 * @param newState
	 */
	public StateChangeEvent(Object source, State newState) {
		super(source);
		this.newState = newState;
	}

	/**
	 * @return the newState
	 */
	public State getNewState() {
		return newState;
	}

	/**
	 * @param newState the newState to set
	 */
	public void setNewState(State newState) {
		this.newState = newState;
	}
}
