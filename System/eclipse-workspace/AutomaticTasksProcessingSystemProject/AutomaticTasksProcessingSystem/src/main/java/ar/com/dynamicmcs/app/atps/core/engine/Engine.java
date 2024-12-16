/*
 * 		Engine.java						Nov 18, 2024
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Component
public class Engine {
	private State state;
	private ApplicationEventPublisher eventPublisher;

	private static final Logger log = LogManager.getLogger(Engine.class);

	public enum State {
		STOPPED, STARTING, RUNNING, STOPPING
	}

	/**
	 * @param state
	 */
	public Engine(ApplicationEventPublisher eventPublisher) {
		super();
		this.eventPublisher = eventPublisher;
		setState(State.STOPPED);
	}

	/**
	 * 
	 */
	public boolean changeState() {
		boolean result = true;
		if (state == State.STOPPED)
			result = start();
		else if (state == State.RUNNING)
			result = stop();
		return result;
	}

	private boolean start() {
		setState(State.STARTING);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setState(State.RUNNING);
		return false;
	}

	private boolean stop() {
		setState(State.STOPPING);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setState(State.STOPPED);
		return false;
	}

	/**
	 * @return the State
	 */
	public State getState() {
		return state;
	}

	private void setState(State state) {
		this.state = state;
		eventPublisher.publishEvent(new EngineStateChangeEvent(this, state));
		log.info("Engine state changed to {}", state.name());
	}
}
