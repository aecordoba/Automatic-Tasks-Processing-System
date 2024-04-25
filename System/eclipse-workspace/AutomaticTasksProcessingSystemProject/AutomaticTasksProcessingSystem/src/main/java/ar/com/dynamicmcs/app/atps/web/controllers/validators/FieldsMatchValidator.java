/*
 * 		FieldsMatchValidator.java
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

package ar.com.dynamicmcs.app.atps.web.controllers.validators;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(final FieldsMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final String firstObj = BeanUtils.getProperty(value, firstFieldName);
			final String secondObj = BeanUtils.getProperty(value, secondFieldName);
			valid = firstObj.isBlank() && secondObj.isBlank() || !firstObj.isBlank() && firstObj.equals(secondObj);
		} catch (final Exception ignore) {
			// ignore
		}
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(firstFieldName)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
		}
		return valid;
	}
}
