/*
 * 		EngineStateService.java						Dec 18, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine.services;

import ar.com.dynamicmcs.app.atps.core.engine.states.EngineStates;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
public interface EngineStateService {
	void startEngine();

	void stopEngine();

	EngineStates getCurrentState();
}
