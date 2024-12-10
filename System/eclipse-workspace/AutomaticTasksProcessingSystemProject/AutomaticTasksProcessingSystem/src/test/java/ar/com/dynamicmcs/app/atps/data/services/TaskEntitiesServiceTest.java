/*
 * 		TaskEntitiesServiceTest.java						Dec 9, 2024
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.dynamicmcs.app.atps.data.model.TaskEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.TasksRepository;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@ExtendWith(MockitoExtension.class)
public class TaskEntitiesServiceTest {
	@Mock
	private TasksRepository tasksRepository;
	@InjectMocks
	private TaskEntitiesServiceImplementation taskEntitiesServiceImplementation;
	private static TaskEntity testTaskEntity;

	@BeforeAll
	public static void setup() {
		testTaskEntity = TestJobEntityUtility.getTestTaskEntity();
	}

	@Test
	@DisplayName("Save TaskEntity test.")
	public void givenTaskEntity_whenSaveTaskEntity_thenReturnTaskEntity() {
		given(tasksRepository.save(testTaskEntity)).willReturn(testTaskEntity);
		TaskEntity savedTaskEntity = taskEntitiesServiceImplementation.saveTaskEntity(testTaskEntity);
		assertThat(savedTaskEntity).isNotNull();
	}

}
