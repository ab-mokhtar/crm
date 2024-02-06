package com.mosofty.crm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CreateUserRequest(
  @NotBlank @Email String username,
  @NotBlank String fullName,
  @NotBlank String password,
  @NotBlank String rePassword,
		List<RoleView> authorities) {

  public CreateUserRequest {
    if (authorities == null) {
      authorities = new ArrayList<>();
    }
  }

  public CreateUserRequest(
    String username,
    String fullName,
    String password,
    String rePassword
  ) {
    this(username, fullName, password, rePassword, new ArrayList<>());
  }

  public CreateUserRequest(
    String username,
    String fullName,
    String password
  ) {
    this(username, fullName, password, password, new ArrayList<>());
  }
}
