/*
 * 		DataEntity.java						Dec 3, 2024
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

package ar.com.dynamicmcs.app.atps.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Entity
@Table(name = "Data")
public class DataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "job")
	private JobEntity jobEntity;
	@Column(name = "arg_order")
	@NotNull
	@Min(value = 1)
	private Integer argumentOrder;
	@NotNull
	private String data;

	/**
	 * @param jobEntity
	 * @param argumentOrder
	 * @param data
	 */
	public DataEntity(@NotNull JobEntity jobEntity, @NotNull @Min(1) Integer argumentOrder, @NotNull String data) {
		super();
		this.jobEntity = jobEntity;
		this.argumentOrder = argumentOrder;
		this.data = data;
	}

	/**
	 * 
	 */
	public DataEntity() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataEntity [id=" + id + ", jobEntity=" + jobEntity + ", argumentOrder=" + argumentOrder + ", data="
				+ data + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the jobEntity
	 */
	public JobEntity getJobEntity() {
		return jobEntity;
	}

	/**
	 * @param jobEntity the jobEntity to set
	 */
	public void setJobEntity(JobEntity jobEntity) {
		this.jobEntity = jobEntity;
	}

	/**
	 * @return the argumentOrder
	 */
	public Integer getArgumentOrder() {
		return argumentOrder;
	}

	/**
	 * @param argumentOrder the argumentOrder to set
	 */
	public void setArgumentOrder(Integer argumentOrder) {
		this.argumentOrder = argumentOrder;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
}
