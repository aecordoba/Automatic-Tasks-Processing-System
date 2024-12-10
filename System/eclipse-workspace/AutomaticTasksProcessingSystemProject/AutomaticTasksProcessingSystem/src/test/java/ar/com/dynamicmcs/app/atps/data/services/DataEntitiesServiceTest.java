/*
 * 		DataEntitiesServiceTest.java						Dec 9, 2024
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

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.DataRepository;
import ar.com.dynamicmcs.app.atps.data.util.TestJobEntityUtility;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@ExtendWith(MockitoExtension.class)
public class DataEntitiesServiceTest {
	@Mock
	private DataRepository dataRepository;
	@InjectMocks
	private DataEntitiesServiceImplementation dataEntitiesServiceImplementation;
	private static DataEntity testDataEntity;
	private static JobEntity testJobEntity;
	private static List<DataEntity> testDataEntitiesList;

	@BeforeAll
	public static void setup() {
		testDataEntity = TestJobEntityUtility.getTestDataEntity();
		testJobEntity = TestJobEntityUtility.getTestJobEntity();
		testDataEntitiesList = TestJobEntityUtility.getTestDataEntitiesList();
	}

	@Test
	@DisplayName("Get DataEntitiesList for JobEntity Id test.")
	public void givenSavedDataEntities_whenGetDataEntitiesListByJobEntityId_thenReturnAllDataEntitiesList() {
		given(dataRepository.findByJobEntityId(testJobEntity.getId())).willReturn(testDataEntitiesList);
		List<DataEntity> resultList = dataEntitiesServiceImplementation.getDataEntitiesList(testJobEntity.getId());
		assertThat(resultList).containsAll(testDataEntitiesList);
	}

	@Test
	@DisplayName("Save DataEntity test.")
	public void givenDataEntity_whenSaveDataEntity_thenReturnDataEntity() {
		given(dataRepository.save(testDataEntity)).willReturn(testDataEntity);
		DataEntity savedDataEntity = dataEntitiesServiceImplementation.saveDataEntity(testDataEntity);
		assertThat(savedDataEntity).isNotNull();
	}

}
