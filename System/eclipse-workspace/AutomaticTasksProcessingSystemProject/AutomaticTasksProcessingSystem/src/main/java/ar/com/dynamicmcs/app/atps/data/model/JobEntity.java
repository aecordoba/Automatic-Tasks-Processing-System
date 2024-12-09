/*
 * 		JobEntity.java						Dec 3, 2024
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
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Entity
@Table(name = "Jobs")
public class JobEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "task"/* , referencedColumnName = "id" */)
	@NotNull
	private TaskEntity taskEntity;

	/**
	 * @param name
	 * @param taskEntity
	 */
	public JobEntity(@NotNull String name, @NotNull TaskEntity taskEntity) {
		super();
		this.name = name;
		this.taskEntity = taskEntity;
	}

	/**
	 * 
	 */
	public JobEntity() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobEntity [id=" + id + ", name=" + name + ", taskEntity=" + taskEntity + "]";
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the taskEntity
	 */
	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	/**
	 * @param taskEntity the taskEntity to set
	 */
	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}
}
