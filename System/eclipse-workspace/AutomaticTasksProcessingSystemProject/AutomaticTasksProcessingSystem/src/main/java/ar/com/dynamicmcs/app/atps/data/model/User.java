/*
 * 		User.java						Nov 17, 2024
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author Adrián E. Córdoba [software.dynamicmcs@gmail.com]
 */
@Entity
@Table(name = "Users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String password;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "Users_Authorities", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "authority"))
	private Set<Authority> authoritiesSet = new HashSet<>();
	private boolean enabled;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param authorities
	 * @param enabled
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public User(Integer id, String name, String password, Set<Authority> authoritiesSet, boolean enabled,
			String firstName,
			String middleName, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.authoritiesSet = authoritiesSet;
		this.enabled = enabled;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * @param name
	 * @param password
	 * @param authorities
	 * @param enabled
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public User(String name, String password, Set<Authority> authoritiesSet, boolean enabled, String firstName,
			String middleName, String lastName) {
		super();
		this.name = name;
		this.password = password;
		this.authoritiesSet = authoritiesSet;
		this.enabled = enabled;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

	public void addAuthority(Authority authority) {
		authoritiesSet.add(authority);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority : authoritiesSet) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", enabled=" + enabled + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(enabled, firstName, id, lastName, middleName, name, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return enabled == other.enabled && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(middleName, other.middleName)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the authorities
	 */
	public Set<Authority> getAuthoritiesSet() {
		return authoritiesSet;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthoritiesSet(Set<Authority> authorities) {
		this.authoritiesSet = authorities;
	}
}
