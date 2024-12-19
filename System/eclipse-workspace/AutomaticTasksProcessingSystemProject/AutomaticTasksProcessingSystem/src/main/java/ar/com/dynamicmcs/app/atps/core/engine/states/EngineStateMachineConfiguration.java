/*
 * 		EngineStateMachineConfiguration.java						Dec 17, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine.states;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import ar.com.dynamicmcs.app.atps.core.engine.Engine;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Configuration
@EnableStateMachineFactory
public class EngineStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<EngineStates, EngineEvents> {
	private final Engine engine;

	/**
	 * @param engine
	 */
	public EngineStateMachineConfiguration(Engine engine) {
		super();
		this.engine = engine;
	}

	@Override
	public void configure(StateMachineStateConfigurer<EngineStates, EngineEvents> states) throws Exception {
		states
				.withStates()
				.initial(EngineStates.STOPPED)
				.states(EnumSet.allOf(EngineStates.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<EngineStates, EngineEvents> transitions) throws Exception {
		transitions
				.withExternal()
				.source(EngineStates.STOPPED)
				.target(EngineStates.RUNNING)
				.event(EngineEvents.START)
				.name(EngineEvents.START.name())
				.action(context -> {
					engine.start();
				})
				.and()
				.withExternal()
				.source(EngineStates.RUNNING)
				.target(EngineStates.STOPPED)
				.event(EngineEvents.STOP)
				.name(EngineEvents.STOP.name())
				.action(context -> {
					engine.stop();
				});
	}
}
