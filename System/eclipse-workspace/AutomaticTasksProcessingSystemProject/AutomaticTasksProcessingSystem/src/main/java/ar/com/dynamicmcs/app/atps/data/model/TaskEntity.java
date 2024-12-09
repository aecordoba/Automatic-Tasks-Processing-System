/*
 * 		TaskEntity.java						Dec 2, 2024
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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Entity
@Table(name = "Tasks")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Column(name = "jar_path")
	private String jarPath;
	@Column(name = "full_class_name")
	@NotNull
	private String fullClassName;

	/**
	 * @param name
	 * @param jarPath
	 * @param fullClassName
	 */
	public TaskEntity(@NotNull String name, @NotNull String jarPath, @NotNull String fullClassName) {
		super();
		this.name = name;
		this.jarPath = jarPath;
		this.fullClassName = fullClassName;
	}

	/**
	 * 
	 */
	public TaskEntity() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskEntity [id=" + id + ", name=" + name + ", jarPath=" + jarPath + ", fullClassName=" + fullClassName
				+ "]";
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
	 * @return the jarPath
	 */
	public String getJarPath() {
		return jarPath;
	}

	/**
	 * @param jarPath the jarPath to set
	 */
	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

	/**
	 * @return the fullClassName
	 */
	public String getFullClassName() {
		return fullClassName;
	}

	/**
	 * @param fullClassName the fullClassName to set
	 */
	public void setFullClassName(String fullClassName) {
		this.fullClassName = fullClassName;
	}
}
