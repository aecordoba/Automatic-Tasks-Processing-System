/*
 * 		Loader.java
 *   Copyright (C) 2024  Adrián E. Córdoba [software.dynamicmcs@gmail.com]
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

package ar.com.dynamicmcs.app.atps.core.jobs.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.stereotype.Component;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class Loader {

	/**
	 * 
	 * @param jarURLs
	 * @param taskFullName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T> T getInstance(URL[] jarURLs, String taskFullName)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		URLClassLoader classLoader = new URLClassLoader(jarURLs, this.getClass().getClassLoader());
		@SuppressWarnings("unchecked")
		Class<T> taskClass = (Class<T>) Class.forName(taskFullName, true, classLoader);
		Constructor<T> constructor = taskClass.getConstructor();
		return constructor.newInstance();
	}
}
