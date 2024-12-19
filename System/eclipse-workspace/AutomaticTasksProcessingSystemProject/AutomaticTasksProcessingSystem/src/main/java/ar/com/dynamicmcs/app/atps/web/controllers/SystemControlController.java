/*
 * 		SystemControlController.java
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

package ar.com.dynamicmcs.app.atps.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.dynamicmcs.app.atps.core.engine.services.EngineStateService;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Controller
@RequestMapping("/system-control")
public class SystemControlController {
	private final EngineStateService engineStateService;

	/**
	 * @param engine
	 */
	public SystemControlController(EngineStateService engineStateService) {
		super();
		this.engineStateService = engineStateService;
	}

	@GetMapping
	public String systemControlForm() {
		return "private/system-control";
	}

	@PostMapping
	public String changeEngineState(@RequestParam("action") String action, Model model) {
		System.out.println(action);
		if (action.equals("start"))
			engineStateService.startEngine();
		else
			engineStateService.stopEngine();
		return "private/system-control";
	}
}
