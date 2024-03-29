/*
 * 		EngineStateMachineTest.java
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
 * 		EngineStateMachineTest.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 27, 2024
 */
package ar.com.dynamicmcs.app.atps.core.engine.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
class EngineStateMachineTest {
	private EngineStateMachine engineStateMachine;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Transitions of the state machine.")
	void testChangeState() {
		engineStateMachine = new EngineStateMachine();
		assertEquals(StateName.STOPPED, engineStateMachine.getCurrentStateName());
		engineStateMachine.changeState();
		assertEquals(StateName.STARTING, engineStateMachine.getCurrentStateName());
		engineStateMachine.changeState();
		assertEquals(StateName.RUNNING, engineStateMachine.getCurrentStateName());
		engineStateMachine.changeState();
		assertEquals(StateName.STOPPING, engineStateMachine.getCurrentStateName());
		engineStateMachine.changeState();
		assertEquals(StateName.STOPPED, engineStateMachine.getCurrentStateName());
	}

}
