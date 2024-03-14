/*
 * 		Stopped.java
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
 * 		Stopped.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 1, 2024
 */
package ar.com.dynamic.app.atps.core.engine.state;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class Stopped implements State {
	private EngineStateMachine engineStateMachine;

	/**
	 * @param engineStateMachine
	 */
	public Stopped(EngineStateMachine engineStateMachine) {
		super();
		this.engineStateMachine = engineStateMachine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.kaikyo.app.atps.core.engine.state.State#changeState()
	 */
	@Override
	public void changeState() {
		engineStateMachine.setState(engineStateMachine.getStarting());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.kaikyo.app.atps.core.engine.state.State#getName()
	 */
	@Override
	public StateName getName() {
		return StateName.STOPPED;
	}

}
