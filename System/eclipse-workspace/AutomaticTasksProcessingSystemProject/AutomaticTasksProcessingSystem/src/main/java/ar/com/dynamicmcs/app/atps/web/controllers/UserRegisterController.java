/*
 * 		UserRegisterController.java
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

package ar.com.dynamicmcs.app.atps.web.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.dynamicmcs.app.atps.data.model.Authority;
import ar.com.dynamicmcs.app.atps.data.services.AuthoritiesService;
import ar.com.dynamicmcs.app.atps.data.services.UsersService;
import ar.com.dynamicmcs.app.atps.web.controllers.dto.UserRegister;
import jakarta.validation.Valid;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Controller
@RequestMapping("/user-register")
public class UserRegisterController {
	private static final Logger log = LogManager.getLogger(UserRegisterController.class);

	private UsersService usersService;
	private AuthoritiesService authoritiesService;
	private PasswordEncoder passwordEncoder;

	/**
	 * @param usersService
	 * @param authoritiesService
	 * @param passwordEncoder
	 */
	public UserRegisterController(UsersService usersService, AuthoritiesService auhoritiesService,
			PasswordEncoder passwordEncoder) {
		super();
		this.usersService = usersService;
		this.authoritiesService = auhoritiesService;
		this.passwordEncoder = passwordEncoder;
	}

	@ModelAttribute(name = "userRegister")
	public UserRegister getUserRegister() {
		return new UserRegister();
	}

	@ModelAttribute(name = "authorities")
	public List<Authority> getAuthorities() {
		return authoritiesService.getAuthoritiesList();
	}

	@GetMapping
	public String userRegisterForm() {
		return "private/user-register";
	}

	@PostMapping
	public String processUserRegister(@Valid UserRegister userRegister, Errors errors, Model model) {
		if (errors.hasErrors())
			return "private/user-register";
		else {
			try {
				usersService.createUser(userRegister.getUser(passwordEncoder));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				log.info("User '{}' registered by '{}'.", userRegister.getName(), auth.getName());
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("exception", "common.exception.dataintegrityviolation");
				return "private/user-register";
			}

		}
		return "redirect:/";
	}
}
