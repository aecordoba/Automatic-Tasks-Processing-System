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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String password;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "Users_Authorities", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "authority"))
	private Set<Authority> authoritiesSet = new HashSet<>();
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	private boolean enabled;
	private boolean locked;
	@Column(name = "account_expired")
	private boolean accountExpired;
	@Column(name = "credentials_expired")
	private boolean credentialsExpired;

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param authoritiesSet
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param enabled
	 * @param locked
	 * @param accountExpired
	 * @param credentialsExpired
	 */
	public User(Integer id, String name, String password, Set<Authority> authoritiesSet, String firstName,
			String middleName, String lastName, boolean enabled, boolean locked, boolean accountExpired,
			boolean credentialsExpired) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.authoritiesSet = authoritiesSet;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.locked = locked;
		this.accountExpired = accountExpired;
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * @param name
	 * @param password
	 * @param authoritiesSet
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param enabled
	 * @param locked
	 * @param accountExpired
	 * @param credentialsExpired
	 */
	public User(String name, String password, Set<Authority> authoritiesSet, String firstName, String middleName,
			String lastName, boolean enabled, boolean locked, boolean accountExpired, boolean credentialsExpired) {
		super();
		this.name = name;
		this.password = password;
		this.authoritiesSet = authoritiesSet;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.locked = locked;
		this.accountExpired = accountExpired;
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * @param name
	 * @param password
	 * @param authoritiesSet
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param enabled
	 */
	public User(String name, String password, Set<Authority> authoritiesSet, String firstName, String middleName,
			String lastName, boolean enabled) {
		super();
		this.name = name;
		this.password = password;
		this.authoritiesSet = authoritiesSet;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.enabled = enabled;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * 
	 */
	public String getUsername() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", authoritiesSet=" + authoritiesSet
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", enabled="
				+ enabled + ", locked=" + locked + ", accountExpired=" + accountExpired + ", credentialsExpired="
				+ credentialsExpired + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(accountExpired, authoritiesSet, credentialsExpired, enabled, firstName, id, lastName,
				locked, middleName, name, password);
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
		return accountExpired == other.accountExpired && Objects.equals(authoritiesSet, other.authoritiesSet)
				&& credentialsExpired == other.credentialsExpired && enabled == other.enabled
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && locked == other.locked
				&& Objects.equals(middleName, other.middleName) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
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
	 * @return the authoritiesSet
	 */
	public Set<Authority> getAuthoritiesSet() {
		return authoritiesSet;
	}

	/**
	 * @param authoritiesSet the authoritiesSet to set
	 */
	public void setAuthoritiesSet(Set<Authority> authoritiesSet) {
		this.authoritiesSet = authoritiesSet;
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
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @param credentialsExpired the credentialsExpired to set
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
}
