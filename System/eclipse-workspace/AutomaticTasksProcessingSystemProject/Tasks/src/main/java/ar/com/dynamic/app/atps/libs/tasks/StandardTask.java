/*
 * 		StandardTask.java
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
 * 		StandardTask.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 21, 2024
 */
package ar.com.dynamic.app.atps.libs.tasks;

import java.util.ArrayList;
import java.util.List;

import ar.com.dynamic.app.atps.libs.tasks.tools.Tool;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public abstract class StandardTask implements Task {
	private List<Tool> toolsList;

	/**
	 * 
	 */
	public StandardTask() {
		super();
		this.toolsList = new ArrayList<>();
	}

	/**
	 * @param toolsList
	 */
	public StandardTask(List<Tool> toolsList) {
		super();
		this.toolsList = toolsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.kaikyo.app.atps.lib.tasks.Task#addTool(ar.com.kaikyo.app.atps.lib.
	 * tasks.tools.Tool)
	 */
	public void addTool(Tool tool) {
		toolsList.add(tool);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.kaikyo.app.atps.lib.tasks.Task#run()
	 */
	public boolean run() {
		boolean result = false;
		for (Tool tool : toolsList) {
			if (tool.initialize()) {
				if (tool.execute()) {
					if (tool.clean())
						result = true;
					else
						break;
				} else
					break;
			} else
				break;
		}
		return result;
	};
}
