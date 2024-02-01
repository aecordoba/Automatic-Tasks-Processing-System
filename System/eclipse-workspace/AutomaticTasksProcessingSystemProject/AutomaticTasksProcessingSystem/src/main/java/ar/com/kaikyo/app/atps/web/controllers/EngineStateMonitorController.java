/*
 * 		EngineStateMonitorController.java
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
 * 		EngineStatusMonitorController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 31, 2024
 */
package ar.com.kaikyo.app.atps.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.kaikyo.app.atps.core.engine.state.EngineState;
import ar.com.kaikyo.app.atps.core.engine.state.EngineStateObserver;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@RestController
@CrossOrigin(origins = "*")
public class EngineStateMonitorController extends EngineStateObserver {
	private EngineState engineState;

	/**
	 * 
	 */
	public EngineStateMonitorController(EngineState engineState) {
		super();
		this.engineState = engineState;
		this.engineState.attach(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.kaikyo.app.atps.core.engine.state.EngineStateObserver#update()
	 */
	@Override
	public void update() {
	}

	@RequestMapping(value = "/state-change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EngineState> getNewEngineState() {
		return ResponseEntity.ok(engineState);
	}
}
