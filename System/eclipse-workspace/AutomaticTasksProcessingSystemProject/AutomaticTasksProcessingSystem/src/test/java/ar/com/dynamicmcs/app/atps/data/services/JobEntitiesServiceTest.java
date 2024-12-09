/*
 * 		JobEntitiesServiceTest.java						Dec 7, 2024
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

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.JobsRepository;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@ExtendWith(MockitoExtension.class)
public class JobEntitiesServiceTest {
	@Mock
	private JobsRepository jobsRepository;
	@InjectMocks
	private JobEntitiesServiceImplementation jobEntitiesServiceImplementation;
	private static JobEntity testJobEntity;
	private static List<JobEntity> testJobEntitiesList;

	@BeforeAll
	public static void setup() {
		testJobEntity = TestJobEntityUtility.getTestJobEntity();
		testJobEntitiesList = TestJobEntityUtility.getTestJobEntitiesList();
	}

	@Test
	@DisplayName("JobEntity save test.")
	public void givenJobEntity_whenSaveJobEntity_thenReturnJobEntity() {
		given(jobsRepository.save(testJobEntity)).willReturn(testJobEntity);
		JobEntity savedJobEntity = jobEntitiesServiceImplementation.saveJobEntity(testJobEntity);
		assertThat(savedJobEntity).isNotNull();
	}

	@Test
	@DisplayName("Get JobEntitiesList test.")
	public void givenSavedJobEntities_whenGetJobEntitiesList_thenReturnAllJobEntitiesList() {
		given(jobsRepository.findAll()).willReturn(testJobEntitiesList);
		List<JobEntity> resultList = jobEntitiesServiceImplementation.getJobEntitiesList();
		assertThat(resultList).containsAll(testJobEntitiesList);
	}
}
