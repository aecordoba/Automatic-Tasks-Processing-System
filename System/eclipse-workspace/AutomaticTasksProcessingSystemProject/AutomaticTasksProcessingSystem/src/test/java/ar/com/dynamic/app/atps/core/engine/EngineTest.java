/*
 * 		EngineTest.java
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
 * 		EngineTest.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 29, 2024
 */
package ar.com.dynamic.app.atps.core.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import ar.com.dynamic.app.atps.core.engine.Engine;
import ar.com.dynamic.app.atps.core.engine.state.StateName;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@DisplayName("Engine tests.")
class EngineTest {
	private Engine engine;

	/**
	 * @param engine
	 */
	public EngineTest() {
		super();
		this.engine = new Engine();
	}

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
	 * @param engine
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
	@DisplayName("Transition State at Engine creation.")
	void testEngineCreation() {
		assertEquals(StateName.STOPPED, engine.getState().getName());
	}

	@Test
	@Tag("Performance")
	@DisplayName("Engine start timing.")
	void testStartTimeout() throws InterruptedException {
		assertTimeoutPreemptively(Duration.ofMillis(11000), () -> engine.start());
	}

	@Test
	@Tag("Performance")
	@DisplayName("Engine stop timing.")
	void testStopTimeout() throws InterruptedException {
		assertTimeoutPreemptively(Duration.ofMillis(11000), () -> engine.stop());
	}

}
