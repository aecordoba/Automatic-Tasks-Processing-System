/*
 * 		EngineStateMachine.java
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

package ar.com.dynamicmcs.app.atps.core.engine.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
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
	private ApplicationEventPublisher eventPublisher;

	private static final Logger log = LogManager.getLogger(EngineStateMachine.class);

	/**
	 * 
	 */
	public EngineStateMachine(ApplicationEventPublisher eventPublisher) {
		stopped = new Stopped(this);
		running = new Running(this);
		starting = new Starting(this);
		stopping = new Stopping(this);
		state = stopped;
		this.eventPublisher = eventPublisher;
	}

	public void changeState() {
		state.changeState();
		eventPublisher.publishEvent(new StateChangeEvent(this, state));
		log.info("Engine state changed to {}", state.getName());
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
