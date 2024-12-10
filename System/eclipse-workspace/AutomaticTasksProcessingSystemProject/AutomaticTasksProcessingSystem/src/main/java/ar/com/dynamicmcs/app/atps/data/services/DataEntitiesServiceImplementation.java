/*
 * 		DataEntitiesServiceImplementation.java						Dec 5, 2024
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

import org.springframework.stereotype.Service;

import ar.com.dynamicmcs.app.atps.data.model.DataEntity;
import ar.com.dynamicmcs.app.atps.data.model.JobEntity;
import ar.com.dynamicmcs.app.atps.data.repositories.DataRepository;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Service
public class DataEntitiesServiceImplementation implements DataEntitiesService {
	private DataRepository dataRepository;

	/**
	 * @param dataRepository
	 */
	public DataEntitiesServiceImplementation(DataRepository dataRepository) {
		super();
		this.dataRepository = dataRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.dynamicmcs.app.atps.data.services.DataEntitiesService#getData(java
	 * .lang.Integer)
	 */
	@Override
	public List<DataEntity> getDataEntitiesList(Long jobEntityId) {
		List<DataEntity> dataEntitiesList = dataRepository.findByJobEntityId(jobEntityId);
		return dataEntitiesList;
	}

	@Override
	public List<DataEntity> getDataEntitiesList(JobEntity jobEntity) {
		List<DataEntity> dataEntitiesList = dataRepository.findByJobEntity(jobEntity);
		return dataEntitiesList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.dynamicmcs.app.atps.data.services.DataEntitiesService#
	 * saveDataEntity(ar.com.dynamicmcs.app.atps.data.model.DataEntity)
	 */
	@Override
	public DataEntity saveDataEntity(DataEntity dataEntity) {
		return dataRepository.save(dataEntity);
	}
}
