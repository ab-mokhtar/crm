package com.mosofty.crm.service;

import static java.lang.String.format;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mosofty.crm.dto.CreateUserRequest;
import com.mosofty.crm.dto.Page;
import com.mosofty.crm.dto.SearchUsersQuery;
import com.mosofty.crm.dto.UpdateUserRequest;
import com.mosofty.crm.dto.UserView;
import com.mosofty.crm.dto.UserWithRolesView;
import com.mosofty.crm.mapper.UserEditMapper;
import com.mosofty.crm.mapper.UserViewMapper;
import com.mosofty.crm.model.User;
import com.mosofty.crm.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo userRepo;
	private final UserEditMapper userEditMapper;
	private final UserViewMapper userViewMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserView create(CreateUserRequest request) {
		if (userRepo.findByUsername(request.username()).isPresent()) {
			throw new ValidationException("Username exists!");
		}
		if (!request.password().equals(request.rePassword())) {
			throw new ValidationException("Passwords don't match!");
		}

		var user = userEditMapper.create(request);
		user.setPassword(passwordEncoder.encode(request.password()));

		user = userRepo.save(user);

		return userViewMapper.toUserView(user, null);
	}

	@Transactional
	public UserView update(Long id, UpdateUserRequest request) {
		var user = userRepo.getById(id);
		userEditMapper.update(request, user);

		user = userRepo.save(user);

		return userViewMapper.toUserView(user, null);
	}

	@Transactional
	public UserView upsert(CreateUserRequest request) {
		var optionalUser = userRepo.findByUsername(request.username());

		if (optionalUser.isEmpty()) {
			return create(request);
		} else {
			UpdateUserRequest updateUserRequest = new UpdateUserRequest(request.fullName(), request.authorities());
			return update(optionalUser.get().getId(), updateUserRequest);
		}
	}

	@Transactional
	public UserView delete(Long id) {
		var user = userRepo.getById(id);

		user.setUsername(user.getUsername().replace("@", String.format("_%s@", user.getId().toString())));
		user.setEnabled(false);
		user = userRepo.save(user);

		return userViewMapper.toUserView(user, null);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
	}

	public boolean usernameExists(String username) {
		return userRepo.findByUsername(username).isPresent();
	}

	public UserView getUser(Long id) {
		return userViewMapper.toUserView(userRepo.getById(id), null);
	}

	public List<UserView> searchUsers(Page page, SearchUsersQuery query) {
		List<User> users = userRepo.findByUsernameOrFullNameOrId(query.username(), query.fullName(), query.id());

		return userViewMapper.toUserView(users,null);
	}
	
	

	public List<UserView> listUsers() {
		 List<User> users = userRepo.findAll();
	        return userViewMapper.toUserView(users, null);
	}
	
	/*
	 * public List<UserWithRolesView> listUsersWithRoles() { List<User> users =
	 * userRepo.findAll(); return userViewMapper.toUserWithRolesView(users); }
	 */

	public List<UserWithRolesView> listUsersWithRoles() {
		   List<User> users = userRepo.findAll();
		    return userViewMapper.toUserWithRolesView(users);
	}

}
