/*
 * 		EngineState.java
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
 * 		EngineState.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 28, 2024
 */
package ar.com.kaikyo.app.atps.core.engine.state;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class EngineState {
	public enum State {
		STOPPED, READY, RUNNING, STOPPING
	};

	private State state;
	private List<EngineStateObserver> observersList;

	/**
	 * 
	 */
	public EngineState() {
		super();
		this.state = State.STOPPED;
		this.observersList = new ArrayList<>();
	}

	/**
	 * 
	 * @param observer
	 */
	public void attach(EngineStateObserver observer) {
		observersList.add(observer);
	}

	/**
	 * 
	 */
	public void notifyAllObservers() {
		for (EngineStateObserver observer : observersList)
			observer.update();
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
		notifyAllObservers();
	}
}
