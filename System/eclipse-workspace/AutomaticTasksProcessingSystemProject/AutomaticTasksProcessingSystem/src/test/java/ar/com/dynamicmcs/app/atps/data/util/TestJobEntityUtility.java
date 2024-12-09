/*
 * 		TestJobEntityUtility.java						Dec 7, 2024
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

package ar.com.dynamicmcs.app.atps.data.util;

import java.util.ArrayList;
import java.util.List;

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
public class TestJobEntityUtility {
	public static TaskEntity getTestTaskEntity() {
		return new TaskEntity("test-task", "/dynamicmcs/Development/Tasks/", "ar.com.dynamicmcs.atps.tasks.TestTask");
	}

	public static JobEntity getTestJobEntity() {
		return new JobEntity("test-job", getTestTaskEntity());
	}

	public static List<JobEntity> getTestJobEntitiesList() {
		List<JobEntity> jobEntitiesList = new ArrayList<>();
		JobEntity jobEntity1 = new JobEntity("test-job-1", getTestTaskEntity());
		JobEntity jobEntity2 = new JobEntity("test-job-2", getTestTaskEntity());
		jobEntitiesList.add(jobEntity1);
		jobEntitiesList.add(jobEntity2);
		return jobEntitiesList;
	}

	public static DataEntity getTestDataEntity() {
		return new DataEntity(getTestJobEntity(), 1, "test-data");
	}

	public static List<DataEntity> getTestDataEntitiesList() {
		List<DataEntity> dataEntitiesList = new ArrayList<>();
		DataEntity dataEntity1 = new DataEntity(getTestJobEntity(), 1, "test-data-1");
		DataEntity dataEntity2 = new DataEntity(getTestJobEntity(), 1, "test-data-2");
		dataEntitiesList.add(dataEntity1);
		dataEntitiesList.add(dataEntity2);
		return dataEntitiesList;
	}
}
