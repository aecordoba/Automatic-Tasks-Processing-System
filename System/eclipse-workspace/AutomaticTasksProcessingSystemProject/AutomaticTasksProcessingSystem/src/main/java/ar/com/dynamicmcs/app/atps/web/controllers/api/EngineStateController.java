/*
 * 		EngineStateController.java						Dec 22, 2024
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

package ar.com.dynamicmcs.app.atps.web.controllers.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.dynamicmcs.app.atps.core.engine.services.EngineStateService;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@RestController
@RequestMapping(path = "/api/engine/state", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class EngineStateController {
	private EngineStateService engineStateService;

	/**
	 * 
	 */
	public EngineStateController(EngineStateService engineStateService) {
		this.engineStateService = engineStateService;
	}

	@GetMapping
	public String getEngineState() {
		return engineStateService.getCurrentState().name();
	}
}
