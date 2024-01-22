/*
 * 		Job.java
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
 * 		Job.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 21, 2024
 */
package ar.com.kaikyo.app.atps.core.jobs;

import java.util.ArrayList;
import java.util.List;

import ar.com.kaikyo.app.atps.lib.tasks.Task;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class Job {
	private String name;
	private Task task;
	private List<Object> argumentsList;

	/**
	 * 
	 */
	public Job() {
		super();
		this.argumentsList = new ArrayList<>();
	}

	/**
	 * @param name
	 * @param task
	 * @param argumentsList
	 */
	public Job(String name, Task task, List<Object> argumentsList) {
		super();
		this.name = name;
		this.task = task;
		this.argumentsList = argumentsList;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the argumentsList
	 */
	public List<Object> getArgumentsList() {
		return argumentsList;
	}

	/**
	 * @param argumentsList the argumentsList to set
	 */
	public void setArgumentsList(List<Object> argumentsList) {
		this.argumentsList = argumentsList;
	}
}
