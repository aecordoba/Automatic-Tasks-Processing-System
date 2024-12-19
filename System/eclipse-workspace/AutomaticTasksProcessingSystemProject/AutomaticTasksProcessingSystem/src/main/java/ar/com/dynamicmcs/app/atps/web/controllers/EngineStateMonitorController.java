/*
 * 		EngineStateMonitorController.java						Dec 14, 2024
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

package ar.com.dynamicmcs.app.atps.web.controllers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.dynamicmcs.app.atps.core.engine.states.EngineEvents;
import ar.com.dynamicmcs.app.atps.core.engine.states.EngineStates;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@RestController
@CrossOrigin(origins = "*")
public class EngineStateMonitorController extends StateMachineListenerAdapter<EngineStates, EngineEvents> {
	private String engineStateName = "STOPPED";
	private boolean transition;
	private EngineStateResponse engineStateResponse;
	private MessageSource messageSource;

	/**
	 * @param engineStateName
	 * @param engineStateResponse
	 */
	public EngineStateMonitorController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
		this.engineStateResponse = new EngineStateResponse();
	}

	@RequestMapping(value = "/state-change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EngineStateResponse> getNewEngineState(Locale locale) {
		String display = "systemcontrol.display." + engineStateName.toLowerCase();
		engineStateResponse.setDisplay(messageSource.getMessage(display, null, locale));
		engineStateResponse.setDisplayClass(engineStateName.toLowerCase());
		engineStateResponse.setOnTransition(transition);
		String action = (engineStateName == EngineStates.STOPPED.name()) ? "start" : "stop";
		engineStateResponse
				.setButtonLabel(messageSource.getMessage("systemcontrol.button.label." + action, null, locale));
		engineStateResponse.setButtonAction(action);

		System.out.println(engineStateResponse);
		return ResponseEntity.ok(engineStateResponse);
	}

	@Override
	public void stateChanged(State<EngineStates, EngineEvents> from, State<EngineStates, EngineEvents> to) {
		this.engineStateName = to.getId().name();
		this.transition = false;
		System.out.println("Monitor controller: " + engineStateName);
		System.out.println("Transition: " + this.transition);
	}

	@Override
	public void transitionStarted(Transition<EngineStates, EngineEvents> transition) {
		this.transition = true;
		System.out.println("Transition: " + this.transition);
	}

	public class EngineStateResponse {
		private String display;
		private String displayClass;
		private boolean onTransition;
		private String buttonLabel;
		private String buttonAction;

		/**
		 * @param display
		 * @param displayClass
		 * @param onTransition
		 * @param buttonLabel
		 * @param buttonAction
		 */
		public EngineStateResponse(String display, String displayClass, boolean onTransition, String buttonLabel,
				String buttonAction) {
			super();
			this.display = display;
			this.displayClass = displayClass;
			this.onTransition = onTransition;
			this.buttonLabel = buttonLabel;
			this.buttonAction = buttonAction;
		}

		/**
		 * 
		 */
		public EngineStateResponse() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "EngineStateResponse [display=" + display + ", displayClass=" + displayClass + ", onTransition="
					+ onTransition + ", buttonLabel=" + buttonLabel + ", buttonAction=" + buttonAction + "]";
		}

		/**
		 * @return the display
		 */
		public String getDisplay() {
			return display;
		}

		/**
		 * @param display the display to set
		 */
		public void setDisplay(String display) {
			this.display = display;
		}

		/**
		 * @return the displayClass
		 */
		public String getDisplayClass() {
			return displayClass;
		}

		/**
		 * @param displayClass the displayClass to set
		 */
		public void setDisplayClass(String displayClass) {
			this.displayClass = displayClass;
		}

		/**
		 * @return the onTransition
		 */
		public boolean isOnTransition() {
			return onTransition;
		}

		/**
		 * @param onTransition the onTransition to set
		 */
		public void setOnTransition(boolean onTransition) {
			this.onTransition = onTransition;
		}

		/**
		 * @return the buttonLabel
		 */
		public String getButtonLabel() {
			return buttonLabel;
		}

		/**
		 * @param buttonLabel the buttonLabel to set
		 */
		public void setButtonLabel(String buttonLabel) {
			this.buttonLabel = buttonLabel;
		}

		/**
		 * @return the buttonAction
		 */
		public String getButtonAction() {
			return buttonAction;
		}

		/**
		 * @param buttonAction the buttonAction to set
		 */
		public void setButtonAction(String buttonAction) {
			this.buttonAction = buttonAction;
		}
	}
}
