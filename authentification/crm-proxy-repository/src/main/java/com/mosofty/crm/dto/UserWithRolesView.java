package com.mosofty.crm.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.mosofty.crm.model.CompteBancaire;
import com.mosofty.crm.model.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserWithRolesView {
    private Long id;
    private String username;
    private String fullName;
    private List<String> roles;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public UserWithRolesView() {
		super();
	}

    
	
	
    
    
}

