/*
 * 		EngineStateChangeEvent.java						Dec 14, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine;

import org.springframework.context.ApplicationEvent;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
public class EngineStateChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private Engine.State engineState;

	/**
	 * @param source
	 * @param newState
	 */
	public EngineStateChangeEvent(Object source, Engine.State engineState) {
		super(source);
		this.engineState = engineState;
	}

	/**
	 * @return the newState
	 */
	public Engine.State getEngineState() {
		return engineState;
	}

	/**
	 * @param newState the newState to set
	 */
	public void setNewState(Engine.State engineState) {
		this.engineState = engineState;
	}
}
