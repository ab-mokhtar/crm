package com.mosofty.crm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;

import com.mosofty.crm.dto.UserView;
import com.mosofty.crm.dto.UserWithRolesView;
import com.mosofty.crm.model.Role;
import com.mosofty.crm.model.User;

@Configuration
public class UserViewMapper {

	public UserView toUserView(User user, String token) {
		return new UserView(user.getId(), user.getUsername(), user.getFullName(),user.getSurname(), token, user.getCompanyName());
	}
	public UserView toUserView(User user ) {
		return new UserView(user.getId(), user.getUsername(), user.getFullName(),user.getSurname(),null,user.getCompanyName());
	}

	public List<UserView> toUserView(List<User> users,String token) {
		return users.stream().map(user -> toUserView(user, token)).toList();
	}

	public List<UserView> toUserView(List<User> users ) {
		return users.stream().map(user -> toUserView(user)).toList();
	}
	
	///////////////////
	
    public UserWithRolesView toUserWithRolesView(User user) {
        UserWithRolesView userWithRolesView = new UserWithRolesView();
        userWithRolesView.setId(user.getId());
        userWithRolesView.setUsername(user.getUsername());
        userWithRolesView.setFullName(user.getFullName());

        List<String> roles = user.getAuthorities().stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList());

        userWithRolesView.setRoles(roles);

        return userWithRolesView;
    }

    public List<UserWithRolesView> toUserWithRolesView(List<User> users) {
        return users.stream()
                .map(this::toUserWithRolesView)
                .collect(Collectors.toList());
    }

}
