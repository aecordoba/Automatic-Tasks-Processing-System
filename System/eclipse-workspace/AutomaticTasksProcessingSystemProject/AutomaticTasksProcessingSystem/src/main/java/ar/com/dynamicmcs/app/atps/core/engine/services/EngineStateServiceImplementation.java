/**
 * 
 */
package ar.com.dynamicmcs.app.atps.core.engine.services;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import ar.com.dynamicmcs.app.atps.core.engine.states.EngineEvents;
import ar.com.dynamicmcs.app.atps.core.engine.states.EngineStates;
import ar.com.dynamicmcs.app.atps.web.controllers.EngineStateMonitorController;
import reactor.core.publisher.Mono;

/**
 * 
 */
@Service
public class EngineStateServiceImplementation implements EngineStateService {
	private final StateMachine<EngineStates, EngineEvents> stateMachine;

	/**
	 * 
	 * @param stateMachineFactory
	 */
	public EngineStateServiceImplementation(StateMachineFactory<EngineStates, EngineEvents> stateMachineFactory,
			EngineStateMonitorController listener) {
		this.stateMachine = stateMachineFactory.getStateMachine();
		this.stateMachine.startReactively().block();
		this.stateMachine.addStateListener(listener);
	}

	/**
	 * 
	 */
	public void startEngine() {
		stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(EngineEvents.START).build())).blockFirst();
	}

	/**
	 * 
	 */
	public void stopEngine() {
		stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(EngineEvents.STOP).build())).blockFirst();
	}

	/**
	 * 
	 * @return
	 */
	public EngineStates getCurrentState() {
		return stateMachine.getState().getId();
	}
}
