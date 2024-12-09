/*
 * 		JobsRepositoryTest.java						Dec 6, 2024
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

package ar.com.dynamicmcs.app.atps.data.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JobsRepositoryTest {
	@Autowired
	private JobsRepository jobsRepository;
	@Autowired
	private TasksRepository tasksRepository;
	private static List<JobEntity> testJobEntitiesList;

	@BeforeAll
	public static void setup() {
		testJobEntitiesList = TestJobEntityUtility.getTestJobEntitiesList();
	}

	@Test
	@DisplayName("Save a JobEntity test.")
	public void saveJobEntityTest() {
		tasksRepository.save(testJobEntitiesList.get(0).getTaskEntity());
		JobEntity savedJobEntity = jobsRepository.save(testJobEntitiesList.get(0));
		Assertions.assertThat(savedJobEntity.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Get all JobEntities test.")
	public void findAllJobEntities() {
		TaskEntity taskEntity = tasksRepository.save(testJobEntitiesList.get(0).getTaskEntity());

		for (JobEntity testJobEntity : testJobEntitiesList) {
			testJobEntity.setTaskEntity(taskEntity);
			jobsRepository.save(testJobEntity);
		}
		List<JobEntity> jobEntitiesListResult = (List<JobEntity>) jobsRepository.findAll();
		Assertions.assertThat(jobEntitiesListResult).hasSameSizeAs(testJobEntitiesList);
	}

}
