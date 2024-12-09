/*
 * 		JobEntitiesServiceImplementation.java						Dec 5, 2024
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

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.DataRepository;
import ar.com.dynamicmcs.app.atps.data.repositories.JobsRepository;
import ar.com.dynamicmcs.app.atps.data.repositories.TasksRepository;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Service
public class JobEntitiesServiceImplementation implements JobEntitiesService {
	private JobsRepository jobsRepository;
	private TasksRepository tasksRepository;
	private DataRepository dataRepository;

	public JobEntitiesServiceImplementation(JobsRepository jobsRepository, TasksRepository tasksRepository,
			DataRepository dataRepository) {
		super();
		this.jobsRepository = jobsRepository;
		this.tasksRepository = tasksRepository;
		this.dataRepository = dataRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.dynamicmcs.app.atps.data.services.JobEntitiesService#saveJobEntity
	 * (ar.com.dynamicmcs.app.atps.data.model.JobEntity)
	 */
	@Override
	@Transactional
	public JobEntity saveJobEntity(JobEntity jobEntity) {
		Optional<TaskEntity> taskEntityOptional = tasksRepository.findByName(jobEntity.getTaskEntity().getName());
		TaskEntity savedTaskEntity = null;
		if (taskEntityOptional.isEmpty())
			savedTaskEntity = tasksRepository.save(jobEntity.getTaskEntity());
		else
			savedTaskEntity = taskEntityOptional.get();
		jobEntity.setTaskEntity(savedTaskEntity);
		return jobsRepository.save(jobEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.dynamicmcs.app.atps.data.services.JobEntitiesService#saveJobEntity
	 * (ar.com.dynamicmcs.app.atps.data.model.JobEntity)
	 */
	@Override
	@Transactional
	public JobEntity saveJobEntity(JobEntity jobEntity, List<DataEntity> dataEntityList) {
		JobEntity savedJobEntity = saveJobEntity(jobEntity);
		for (DataEntity dataEntity : dataEntityList) {
			dataEntity.setJobEntity(savedJobEntity);
			dataRepository.save(dataEntity);
		}
		return savedJobEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.dynamicmcs.app.atps.data.services.JobEntitiesService#
	 * getJobEntities()
	 */
	@Override
	public List<JobEntity> getJobEntitiesList() {
		List<JobEntity> jobsList = (List<JobEntity>) jobsRepository.findAll();
		return jobsList;
	}
}
