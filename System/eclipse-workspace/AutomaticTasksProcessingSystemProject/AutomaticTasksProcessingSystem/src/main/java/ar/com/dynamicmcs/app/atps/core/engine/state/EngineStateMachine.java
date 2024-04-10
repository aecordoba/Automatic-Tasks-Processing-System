/*
 * 		EngineStateMachine.java
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
 * 		EngineStateMachine.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 1, 2024
 */
package ar.com.dynamicmcs.app.atps.core.engine.state;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Service
public class EngineStateMachine {
	private State stopped;
	private State running;
	private State starting;
	private State stopping;
	private State state;

	private List<StateChangeObserver> observers;

	private static final Logger log = LogManager.getLogger(EngineStateMachine.class);

	/**
	 * 
	 */
	public EngineStateMachine() {
		stopped = new Stopped(this);
		running = new Running(this);
		starting = new Starting(this);
		stopping = new Stopping(this);
		state = stopped;
		observers = new ArrayList<>();
	}

	public void changeState() {
		state.changeState();
		for (StateChangeObserver observer : observers)
			try {
				observer.update(getCurrentStateName());
			} catch (Exception exception) {
				// TODO
			}
		log.info("Engine state changed to {}", state.getName());
	}

	/**
	 * 
	 * @param observer
	 */
	public void attachObserver(StateChangeObserver observer) {
		observer.update(getCurrentStateName());
		observers.add(observer);
	}

	/**
	 * @return the state
	 */
	public State getCurrentState() {
		return state;
	}

	/**
	 * 
	 * @return
	 */
	public String getCurrentStateName() {
		return state.getName();
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the stopped
	 */
	public State getStopped() {
		return stopped;
	}

	/**
	 * @return the running
	 */
	public State getRunning() {
		return running;
	}

	/**
	 * @return the starting
	 */
	public State getStarting() {
		return starting;
	}

	/**
	 * @return the stopping
	 */
	public State getStopping() {
		return stopping;
	}
}
