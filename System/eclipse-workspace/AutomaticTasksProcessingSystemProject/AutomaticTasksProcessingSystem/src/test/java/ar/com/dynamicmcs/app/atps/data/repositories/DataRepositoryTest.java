/*
 * 		DataRepositoryTest.java						Dec 6, 2024
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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataRepositoryTest {
	@Autowired
	private DataRepository dataRepository;
	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private JobsRepository jobsRepository;
	private static TaskEntity testTaskEntity;
	private static JobEntity testJobEntity;
	private static DataEntity testDataEntity;

	@BeforeAll
	public static void setup() {
		testTaskEntity = TestJobEntityUtility.getTestTaskEntity();
		testJobEntity = TestJobEntityUtility.getTestJobEntity();
		testDataEntity = TestJobEntityUtility.getTestDataEntity();
	}

	@Test
	// @Rollback(value = false)
	// @Order(1)
	@DisplayName("Save a DataEntity for a JobEntity test.")
	public void saveDataEntityTest() {
		testTaskEntity = tasksRepository.save(testTaskEntity);
		testJobEntity.setTaskEntity(testTaskEntity);
		testJobEntity = jobsRepository.save(testJobEntity);
		testDataEntity.setJobEntity(testJobEntity);
		testDataEntity = dataRepository.save(testDataEntity);

		Assertions.assertThat(testDataEntity.getId()).isGreaterThan(0);
	}

	// @Test
	// @Order(2)
	// @DisplayName("Get a DataEntity for a JobEntity.")
	// public void getDataEntityByJobEntity() {
	// System.out.println("testJob: " + testJob);
	//
	// List<DataEntity> dataEntitiesList =
	// dataRepository.findByJobEntity(testJob);
	// System.out.println(testJob);
	//
	// Assertions.assertThat(dataEntitiesList.get(0).getId()).isEqualTo(1L);
	// }
}