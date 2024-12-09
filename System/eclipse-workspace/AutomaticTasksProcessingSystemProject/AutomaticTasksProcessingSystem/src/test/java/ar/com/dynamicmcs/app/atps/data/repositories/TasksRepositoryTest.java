/*
 * 		TasksRepositoryTest.java						Dec 8, 2024
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

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@DataJpaTest
public class TasksRepositoryTest {
	@Autowired
	private TasksRepository tasksRepository;
	private static TaskEntity testTaskEntity;

	@BeforeAll
	public static void setup() {
		testTaskEntity = TestJobEntityUtility.getTestTaskEntity();
	}

	@Test
	@DisplayName("Save TaskEntity test.")
	public void saveTaskEntityTest() {
		TaskEntity savedTaskEntity = tasksRepository.save(testTaskEntity);
		Assertions.assertThat(savedTaskEntity.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Get TaskEntity by name test.")
	public void getTaskEntityByNameTest() {
		TaskEntity savedTaskEntity = tasksRepository.save(testTaskEntity);
		Optional<TaskEntity> taskEntityResult = tasksRepository.findByName(savedTaskEntity.getName());
		Assertions.assertThat(taskEntityResult.get().getId()).isEqualTo(savedTaskEntity.getId());
	}
}
