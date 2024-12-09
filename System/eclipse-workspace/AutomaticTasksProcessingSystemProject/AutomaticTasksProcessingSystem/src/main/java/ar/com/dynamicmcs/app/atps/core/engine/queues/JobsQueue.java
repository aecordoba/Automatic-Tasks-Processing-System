/*
 * 		JobsQueue.java						Dec 1, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine.queues;

import java.util.concurrent.ArrayBlockingQueue;

import ar.com.dynamicmcs.app.atps.core.jobs.Job;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
public class JobsQueue {
	private ArrayBlockingQueue<Job> queue;
	private String name;

	/**
	 * @param queue
	 * @param name
	 */
	public JobsQueue(int capacity, String name) {
		this.queue = new ArrayBlockingQueue<>(capacity);
		this.name = name;
	}

	/**
	 * }
	 * @param job
	 */

	public void put(Job job) {
		try {
			queue.put(job);
			// Change job status.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the queue
	 */
	public ArrayBlockingQueue<Job> getQueue() {
		return queue;
	}

	/**
	 * @param queue the queue to set
	 */
	public void setQueue(ArrayBlockingQueue<Job> queue) {
		this.queue = queue;
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
}
