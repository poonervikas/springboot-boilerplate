package com.pollsforall.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	private String roleName;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "role")
	private Set<UserRole> userRoles=new HashSet<>();

	public Long getRoleId() {
		return roleId;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", userRoles=" + userRoles + "]";
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}