/*
 * 		SystemControlController.java
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
 * 		SystemControlController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 29, 2024
 */
package ar.com.kaikyo.app.atps.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.kaikyo.app.atps.core.engine.state.EngineState;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Controller
@RequestMapping("/system-control")
public class SystemControlController {
	private EngineState engineState;

	public SystemControlController(EngineState engineState) {
		super();
		this.engineState = engineState;
	}

	@GetMapping
	public String systemControlForm() {
		return "private/system-control";
	}

	@PostMapping
	public String changeEngineState() {
		String state = engineState.getState().toString();
		if (state.equals(EngineState.State.STOPPED.toString())) {
			engineState.setState(EngineState.State.STARTING);
		} else if (state.equals(EngineState.State.STARTING.toString())) {
			engineState.setState(EngineState.State.RUNNING);
		} else if (state.equals(EngineState.State.RUNNING.toString())) {
			engineState.setState(EngineState.State.STOPPING);
		} else if (state.equals(EngineState.State.STOPPING.toString())) {
			engineState.setState(EngineState.State.STOPPED);
		}
		return "private/system-control";
	}
}
