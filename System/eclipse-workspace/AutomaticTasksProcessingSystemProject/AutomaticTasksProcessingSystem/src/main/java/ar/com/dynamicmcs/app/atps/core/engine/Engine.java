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
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 1, 2024
 */
package ar.com.dynamicmcs.app.atps.core.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import ar.com.dynamicmcs.app.atps.core.engine.state.EngineStateMachine;
import ar.com.dynamicmcs.app.atps.core.engine.state.State;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class Engine {
	private EngineStateMachine engineStateMachine;

	private static final Logger log = LogManager.getLogger(Engine.class);

	/**
	 * @param engineStateMachine
	 */
	public Engine(EngineStateMachine engineStateMachine) {
		super();
		this.engineStateMachine = engineStateMachine;
		log.info("Engine created in {} state.", getState().getName());
	}

	/**
	 * 
	 */
	public void changeState() {
		engineStateMachine.changeState();
		if (engineStateMachine.getCurrentStateName().equals("STARTING"))
			start();
		if (engineStateMachine.getCurrentStateName().equals("STOPPING"))
			stop();
	}

	/**
	 * 
	 */
	public void start() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		engineStateMachine.changeState();
	}

	/**
	 * 
	 */
	public void stop() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		engineStateMachine.changeState();
	}

	/**
	 * 
	 * @return
	 */
	public State getState() {
		return engineStateMachine.getCurrentState();
	}
}
