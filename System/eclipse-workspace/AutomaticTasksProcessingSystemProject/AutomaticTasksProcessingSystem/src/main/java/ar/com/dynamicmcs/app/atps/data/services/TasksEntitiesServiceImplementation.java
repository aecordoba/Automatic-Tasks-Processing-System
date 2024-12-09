/*
 * 		TasksEntitiesServiceImplementation.java						Dec 7, 2024
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

package ar.com.dynamicmcs.app.atps.data.services;

import org.springframework.stereotype.Service;

import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.TasksRepository;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Service
public class TasksEntitiesServiceImplementation implements TaskEntitiesService {
	private TasksRepository tasksRepository;

	/**
	 * @param tasksRepository
	 */
	public TasksEntitiesServiceImplementation(TasksRepository tasksRepository) {
		super();
		this.tasksRepository = tasksRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.dynamicmcs.app.atps.data.services.TaskEntitiesService#
	 * saveTaskEntity(ar.com.dynamicmcs.app.atps.data.model.TaskEntity)
	 */
	@Override
	public TaskEntity saveTaskEntity(TaskEntity taskEntity) {
		return tasksRepository.save(taskEntity);
	}

}
