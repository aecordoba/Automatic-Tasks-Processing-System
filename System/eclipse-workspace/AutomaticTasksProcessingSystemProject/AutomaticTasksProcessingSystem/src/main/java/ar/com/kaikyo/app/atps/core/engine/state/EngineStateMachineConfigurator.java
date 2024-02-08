/*
 * 		EngineStateMachineConfigurator.java
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
 * 		EngineStateMachineConfigurator.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 5, 2024
 */
package ar.com.kaikyo.app.atps.core.engine.state;

import java.util.EnumSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import ar.com.kaikyo.app.atps.core.engine.Engine;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Configuration
@EnableStateMachine
public class EngineStateMachineConfigurator extends EnumStateMachineConfigurerAdapter<EngineStates, EngineEvents> {
	private static final Logger log = LogManager.getLogger(EngineStateMachineConfigurator.class);

	private Engine engine;

	/**
	 * @param engine
	 */
	public EngineStateMachineConfigurator(Engine engine) {
		super();
		this.engine = engine;
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<EngineStates, EngineEvents> config)
			throws Exception {
		config
				.withConfiguration()
				.autoStartup(true)
				.machineId("ENGINE STATE MACHINE")
				.listener(listener());
	}

	@Override
	public void configure(StateMachineStateConfigurer<EngineStates, EngineEvents> states)
			throws Exception {
		states
				.withStates()
				.initial(EngineStates.STOPPED)
				.states(EnumSet.allOf(EngineStates.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<EngineStates, EngineEvents> transitions)
			throws Exception {
		transitions
				.withExternal()
				.source(EngineStates.STOPPED).target(EngineStates.RUNNING).event(EngineEvents.CHANGE)
				.action(start())
				.and()
				.withExternal()
				.source(EngineStates.RUNNING).target(EngineStates.STOPPED).event(EngineEvents.CHANGE)
				.action(stop());
	}

	@Bean
	public StateMachineListener<EngineStates, EngineEvents> listener() {
		return new StateMachineListenerAdapter<EngineStates, EngineEvents>() {
			@Override
			public void stateChanged(State<EngineStates, EngineEvents> from, State<EngineStates, EngineEvents> to) {
				log.info("Engine state changed to {}.", to.getId());
			}
		};
	}

	@Bean
	public Action<EngineStates, EngineEvents> start() {
		return new Action<EngineStates, EngineEvents>() {

			@Override
			public void execute(StateContext<EngineStates, EngineEvents> context) {
				engine.start();
			}
		};
	}

	@Bean
	public Action<EngineStates, EngineEvents> stop() {
		return new Action<EngineStates, EngineEvents>() {

			@Override
			public void execute(StateContext<EngineStates, EngineEvents> context) {
				engine.stop();
			}
		};
	}
}
