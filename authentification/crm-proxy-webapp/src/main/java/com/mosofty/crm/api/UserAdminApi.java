package com.mosofty.crm.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mosofty.crm.dto.CreateUserRequest;
import com.mosofty.crm.dto.ListResponse;
import com.mosofty.crm.dto.SearchRequest;
import com.mosofty.crm.dto.SearchUsersQuery;
import com.mosofty.crm.dto.UpdateUserRequest;
import com.mosofty.crm.dto.UserView;
import com.mosofty.crm.dto.UserWithRolesView;
import com.mosofty.crm.model.Role;
import com.mosofty.crm.service.UserService;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "UserAdmin")
@RestController
@RequestMapping(path = "api/admin/user")
@CrossOrigin(origins = "*")
//@RolesAllowed(Role.SUPER_ADMIN)
@RequiredArgsConstructor
public class UserAdminApi {

	private final UserService userService;

	@PostMapping
	public UserView create(@RequestBody @Valid CreateUserRequest request) {
		return userService.create(request);
	}

	@PutMapping("{id}")
	public UserView update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
		return userService.update(id, request);
	}

	@DeleteMapping("{id}")
	public UserView delete(@PathVariable Long id) {
		return userService.delete(id);
	}

	@GetMapping("{id}")
	public UserView get(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@PostMapping("search")
	public ListResponse<UserView> search(@RequestBody SearchRequest<SearchUsersQuery> request) {
		return new ListResponse<UserView>(userService.searchUsers(request.page(), request.query()));
	}
	
	
	  @GetMapping("list")
	    public ListResponse<UserView> listUsers() {
	        List<UserView> users = userService.listUsers();
	        return new ListResponse<>(users);
	    }
	  
	  @GetMapping("list-with-roles")
	    public List<UserWithRolesView> listUsersWithRoles() {
	        return userService.listUsersWithRoles();
	    }
	  
	//  @GetMapping("list-with-roles")
	//  public ListResponse<UserWithRolesView> listUsersWithRoles() {
	//      List<UserWithRolesView> usersWithRoles = userService.listUsersWithRoles();
	//      return new ListResponse<>(usersWithRoles);
	//  }


	
}
