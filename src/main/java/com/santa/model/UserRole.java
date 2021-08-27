package com.santa.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRole")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "userRole", nullable = false)
	private String userRole;

	public UserRole() {
		super();
	}

	public UserRole(String userRole) {
		super();
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return userRole;
	}

	public void setRole(String role) {
		this.userRole = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return id == other.id && Objects.equals(userRole, other.userRole);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + userRole + "]";
	}

}
