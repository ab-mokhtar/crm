package com.mosofty.crm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.mosofty.crm.dto.CreateUserRequest;
import com.mosofty.crm.dto.RoleView;
import com.mosofty.crm.dto.UpdateUserRequest;
import com.mosofty.crm.model.Role;
import com.mosofty.crm.model.User;

@Configuration
public class UserEditMapper {

	public User create(CreateUserRequest request) {

		User user = new User();
		user.setUsername(request.username());
		user.setPassword(request.password());
		user.setFullName(request.fullName());
		user.setAuthorities(stringToRole(request.authorities()));
		return user;

	}

	public void update(UpdateUserRequest request, User user) {
		user.setAuthorities(stringToRole(request.authorities()));
	}

	protected List<Role> stringToRole(List<RoleView> authorities) {
		if (authorities != null) {
			return authorities.stream().map(Role::new).toList();
		}
		return new ArrayList<>();
	}

}
