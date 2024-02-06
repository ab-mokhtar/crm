package com.mosofty.crm.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Builder;

public record UpdateUserRequest(
  @NotBlank
  String fullName,
		List<RoleView> authorities
) {

  @Builder
  public UpdateUserRequest {
  }

  public UpdateUserRequest() {
    this(null, null);
  }
}
