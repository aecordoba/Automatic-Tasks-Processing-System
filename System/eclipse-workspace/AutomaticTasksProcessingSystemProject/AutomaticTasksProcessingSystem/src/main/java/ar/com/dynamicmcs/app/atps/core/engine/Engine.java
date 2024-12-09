/*
 * 		Engine.java						Nov 18, 2024
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

package ar.com.dynamicmcs.app.atps.core.engine;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.services.DataEntitiesService;
import ar.com.dynamicmcs.app.atps.data.services.JobEntitiesService;
import ar.com.dynamicmcs.app.atps.data.services.TaskEntitiesService;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Component
public class Engine {
	private TaskEntitiesService taskEntitiesService;
	private JobEntitiesService jobEntitiesService;
	private DataEntitiesService dataEntitiesService;

	/**
	 * @param jobEntitiesService
	 * @param dataEntitiesService
	 */
	public Engine(JobEntitiesService jobEntitiesService, DataEntitiesService dataEntitiesService,
			TaskEntitiesService taskEntitiesService) {
		super();
		this.jobEntitiesService = jobEntitiesService;
		this.dataEntitiesService = dataEntitiesService;
		this.taskEntitiesService = taskEntitiesService;
	}

	/**
	 * 
	 */
	public boolean changeState() {
		System.out.println("Creando job...");

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setName("tarea1");
		taskEntity.setJarPath("/home/adrian/Development/Tasks/");
		taskEntity.setFullClassName("ar.com.Tareas.tarea1");
		taskEntitiesService.saveTaskEntity(taskEntity);

		JobEntity jobEntity1 = new JobEntity();
		jobEntity1.setName("trabajo1");
		jobEntity1.setTaskEntity(taskEntity);
		jobEntitiesService.saveJobEntity(jobEntity1);

		JobEntity jobEntity2 = new JobEntity();
		jobEntity2.setName("trabajo2");
		jobEntity2.setTaskEntity(taskEntity);
		jobEntitiesService.saveJobEntity(jobEntity2);

		DataEntity dataEntity1 = new DataEntity();
		dataEntity1.setArgumentOrder(1);
		dataEntity1.setData("dato-1");
		dataEntity1.setJobEntity(jobEntity1);
		DataEntity dataEntity2 = new DataEntity();
		dataEntity2.setArgumentOrder(2);
		dataEntity2.setData("dato-2");
		dataEntity2.setJobEntity(jobEntity1);

		dataEntitiesService.saveDataEntity(dataEntity1);
		dataEntitiesService.saveDataEntity(dataEntity2);

		System.out.println("Job guardado!");

		List<JobEntity> jobsList = jobEntitiesService.getJobEntitiesList();
		for (JobEntity job : jobsList) {
			System.out.println(job);
			List<DataEntity> dataList = dataEntitiesService.getDataEntitiesList(job.getId());
			for (DataEntity data : dataList)
				System.out.println(data);
		}

		return true;
	}
}
